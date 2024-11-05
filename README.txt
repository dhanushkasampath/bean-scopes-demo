Singleton
=========

note: every time when we make this GET call, it will increment the count.
That means only single 'SingletonScopedBean' object has been created throughout the entire application

http://localhost:8081/api/singleton
Counter value: 3

Controller Injection: Injecting the singleton bean in the controller means the same
instance is used each time the controller handles a request.

prototype
=========

note: every time when we make this GET call, it will return the current time in millis.
That means new object of  'PrototypeScopedBean' is created at every request

http://localhost:8081/api/prototype
Prototype instance ID: Instance-1730791153895

Unique Instance: The instanceId field allows us to track and verify that each request generates a new
instance of the prototype bean.

Request
Session
Application
WebSocket



