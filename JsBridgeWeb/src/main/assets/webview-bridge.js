 if(!window.LchJsApi){
 window.LchJsApi={events:{}}


          window.LchJsApi.onNativeEvent=function(eventName,param){
           console.log(`onNativeEvent:`+eventName)
           console.log(`onNativeEvent window.LchJsApi.events:`+Object.keys(window.LchJsApi.events))

             const eventObj=window.LchJsApi.events[eventName]
              console.log(`onNativeEvent eventObj:`+eventObj)

     if(eventObj){

     for (const key of Object.keys(eventObj)) {
     eventObj[key](eventName,param)
}

     }
        }


    window.$$callNative=function(eventName,param){
    if( window.LchNativeApi&& window.LchNativeApi.onJsEvent){
     window.LchNativeApi.onJsEvent(eventName,param)
     }
    }

     window.$$callNative=function(eventName,param,listenerId,listener){
        if( window.LchNativeApi&& window.LchNativeApi.onJsEvent){
        $$registerEventListener(eventName,listenerId,listener)
         window.LchNativeApi.onJsEvent(eventName,param)
         }
        }

    window.$$registerEventListener=function(eventName,listenerId,listener){
      console.log(`$$registerEventListener:`+eventName)

    if(window.LchJsApi.events[eventName]){
    window.LchJsApi.events[eventName].listenerId=listener
     console.log(`$$registerEventListener:1`)
    }else{
     window.LchJsApi.events[eventName]={listenerId:listener}
     console.log(`$$registerEventListener:2`)
    }

     console.log(`$$registerEventListener after:`+Object.keys(window.LchJsApi.events))
    }

     window.$$removeEventListener=function(eventName,listenerId){
    if(window.LchJsApi.events[eventName]){
delete window.LchJsApi.events[eventName].listenerId
    }
    }


    }