# crypto-sse

### Technologies ###

* Java Spring Boot
* SSE (Server Send Events)
* Event Listeners - Publishers
* Unit tests

### Description ###

Java Spring Boot application (microservice) that has one stream endpoint to 
send to front-end updates about bitcoin price.
The project has 2 different clients in order to take data from different crypto
exchanges.

The application sends request to take btc price every one sec. The 
publisher take this value and it sends notifications to the listeners. When the listeners receive 
the btc value they send updates via sse to the frond end.
With this current architecture, we can have as many clients as we want and 
all of them we can take the same responses from this endpoint.

SSE uses SseEmitter object. SseEmitter is actually a subclass of ResponseBodyEmitter and provides additional Server-Sent Event (SSE) support out-of-the-box.
ResponseBodyEmitter can be used to send multiple objects where each object is written with a compatible HttpMessageConverter.
SseEmitter extends ResponseBodyEmitter and allows you to send many messages in response to a single request, as required by the SSE protocol.

### Test the app ###

* Start the app
* Open from browser http://localhost:8080/crypto-stream

You can open as many windows as you want. The app will do broadcast to all 
browser windows and you will see the same message.
