<template>
    <v-container>
        定胆码
        <v-sheet class="d-flex align-content-start flex-wrap align-center justify-start">
            <v-sheet :key="item.value" v-for="item in dingDanMa">
                <v-checkbox v-model="item.checked" :label="item.lab" color="primary" hide-details />
            </v-sheet>
        </v-sheet>

        杀胆码
        <v-sheet class="d-flex align-content-start flex-wrap align-center justify-start">
            <v-sheet :key="item.value" v-for="item in shaDanMa">
                <v-checkbox v-model="item.checked" :label="item.lab" color="primary" hide-details />
            </v-sheet>
        </v-sheet>

        定和尾
        <v-sheet class="d-flex align-content-start flex-wrap align-center justify-start">
            <v-sheet :key="item.value" v-for="item in dingHeWei">
                <v-checkbox v-model="item.checked" :label="item.lab" color="primary" hide-details />
            </v-sheet>
        </v-sheet>

        定跨度
        <v-sheet class="d-flex align-content-start flex-wrap align-center justify-start">
            <v-sheet :key="item.value" v-for="item in dingKuaDu">
                <v-checkbox v-model="item.checked" :label="item.lab" color="primary" hide-details />
            </v-sheet>
        </v-sheet>

        n码出m个
        <v-sheet class="d-flex align-content-start flex-wrap align-center justify-start">
            <v-sheet :key="item.value" v-for="item in nMa01">
                <v-checkbox v-model="item.checked" :label="item.lab" color="primary" hide-details />
            </v-sheet>
        </v-sheet>
        <v-sheet class="d-flex align-content-start flex-wrap align-center justify-start">
            <v-sheet :key="item.value" v-for="item in nMa01ConComp">
                <v-checkbox v-model="item.checked" :label="item.lab" color="red" hide-details />
            </v-sheet>
        </v-sheet>

        类型
        <v-sheet class="d-flex align-content-start flex-wrap align-center justify-start">
            <v-sheet :key="item.value" v-for="item in types">
                <v-checkbox v-model="item.checked" :label="item.lab" color="primary" hide-details />
            </v-sheet>
        </v-sheet>

        <v-row>
            <v-col class="d-flex justify-space-around align-center">
                <v-btn variant="outlined" color="primary" @click="doFilter">
                    开始缩水
                </v-btn>
            </v-col>
        </v-row>

        <v-dialog v-model="dialog" width="auto">
            <v-card>
                <v-card-text>
                    {{ filterResult }}
                </v-card-text>
                <v-card-actions>
                    <v-btn color="primary" block @click="dialog = false">关闭</v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>

    </v-container>
</template>

<script setup>
import { useRouter } from 'vue-router';
import { ref, onMounted, computed } from 'vue'

const router = useRouter()

const dingDanMa = ref([])
const shaDanMa = ref([])
const dingHeWei = ref([])
const dingKuaDu = ref([])
const nMa01 = ref([])
const nMa01Cn = ref([])
const types = ref([])
const filterResult = ref("")
const dialog = ref(false)

for (let i = 0; i < 10; i++) {
    dingDanMa.value.push({ lab: i.toString(), checked: false, value: i })
}

for (let i = 0; i < 10; i++) {
    shaDanMa.value.push({ lab: i.toString(), checked: false, value: i })
}

for (let i = 0; i < 10; i++) {
    dingHeWei.value.push({ lab: i.toString(), checked: false, value: i })
}

for (let i = 0; i < 10; i++) {
    dingKuaDu.value.push({ lab: i.toString(), checked: false, value: i })
}

for (let i = 0; i < 10; i++) {
    nMa01.value.push({ lab: i.toString(), checked: false, value: i })
}

types.value.push({ lab: '组三', checked: false, value: "2" })
types.value.push({ lab: '组六', checked: false, value: "1" })
types.value.push({ lab: '豹子', checked: false, value: "3" })


function launchFilterPage() {
    router.push({ path: '/filter' })
}

function doFilter() {
    const params = {}

    params.danMa = dingDanMa.value.filter(e => e.checked).map(e => e.value)
    params.shaDanMa = shaDanMa.value.filter(e => e.checked).map(e => e.value)
    params.heWei = dingHeWei.value.filter(e => e.checked).map(e => e.value)
    params.kuaDu = dingKuaDu.value.filter(e => e.checked).map(e => e.value)
    params.nMa = { source: nMa01.value.filter(e => e.checked).map(e => e.value), count: nMa01Cn.value.filter(e => e.checked).map(e => e.value) }
    params.type = types.value.filter(e => e.checked).map(e => e.value)

    if (window.$$callNative) {
        window.$$callNative("filter", JSON.stringify(params), "id", (e, p) => {
            console.log(p)
            let j = JSON.parse(p)
            filterResult.value = j.data
            dialog.value = true
        })
    }
}

const nMa01ConComp = computed(() => {
    let checkedCount = 0

    for (let j = 0; j < nMa01.value.length; j++) {
        if (nMa01.value[j].checked) {
            checkedCount++
        }

    }

    console.log(checkedCount)

    nMa01Cn.value.splice(0, nMa01Cn.value.length)


    if (checkedCount > 0) {
        for (let i = 0; i < checkedCount + 1; i++) {
            nMa01Cn.value.push({ lab: i.toString(), checked: false, value: i })
        }
    }


    return nMa01Cn.value
})

onMounted(() => {

})

</script>