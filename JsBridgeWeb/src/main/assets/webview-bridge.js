if (!window.LchJsApi) {

  window.LchJsApi = { events: {} }


  window.LchJsApi.onNativeEvent = function (eventName, param) {
    console.log(`onNativeEvent:` + eventName)
    console.log(`onNativeEvent window.LchJsApi.events:` + Object.keys(window.LchJsApi.events))

    const eventListenerArr = window.LchJsApi.events[eventName]
    console.log(`onNativeEvent eventListenerArr:` + eventListenerArr)

    if (eventListenerArr) {

      for (const listener of eventListenerArr) {
        listener(eventName, param)
      }

    }
  }


  window.$$callNative = function (eventName, param) {
    if (window.LchNativeApi && window.LchNativeApi.onJsEvent) {
      window.LchNativeApi.onJsEvent(eventName, param)
    }
  }

  window.$$callNative = function (eventName, param, listener) {
    if (window.LchNativeApi && window.LchNativeApi.onJsEvent) {
      window.$$registerEventListener(eventName, listener)
      window.LchNativeApi.onJsEvent(eventName, param)
    }
  }

  window.$$registerEventListener = function (eventName, listener) {
    console.log(`$$registerEventListener:` + eventName)
    let listenersArr = window.LchJsApi.events[eventName]

    if (listenersArr) {
      const found = listenersArr.find(element => element === listener);
      console.log(`$$registerEventListener found:${found}`)
      if (!found) {
        listenersArr.push(listener)
        console.log(`$$registerEventListener:push listener.`)
      }

    } else {
      window.LchJsApi.events[eventName] = [listener]
      console.log(`$$registerEventListener:new listener arr.`)
    }

    console.log(`$$registerEventListener after:` + Object.keys(window.LchJsApi.events))
  }

  window.$$removeEventListener = function (eventName, listener) {
    let listenersArr = window.LchJsApi.events[eventName]

    if (listenersArr) {
      window.LchJsApi.events[eventName] = listenersArr.filter(function (value, index, arr) {
        return value !== listener;
      });
      console.log(`$$removeEventListener after:eventName=${eventName},listener=${listener}`)
      console.log(`$$removeEventListener after event arr:` + window.LchJsApi.events[eventName])
    }
  }


}