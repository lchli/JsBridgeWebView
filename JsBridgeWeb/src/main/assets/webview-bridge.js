 window.LchJsApi={events:{}}

          window.LchJsApi.onNativeEvent=function(eventName,param){
             const eventObj=window.LchJsApi.events.eventName
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
    if(window.LchJsApi.events.eventName){
    window.LchJsApi.events.eventName.listenerId=listener
    }else{
     window.LchJsApi.events.eventName={listenerId:listener}
    }
    }

     window.$$removeEventListener=function(eventName,listenerId){
    if(window.LchJsApi.events.eventName){
delete window.LchJsApi.events.eventName.listenerId
    }
    }