# restful_crud
Tomcat restful server v10 CRUD example

Example of CRUD using JAX-RS and JPA for Tomcat 10 and EclipseLink

Database folder for MySQL: Jardineria

Aspects to underline:
- Restful with user + password sent in HTML Header.
- JAX-RS with List: using a wrapping class: LinkedList_envuelta.
- JAX-RS using JSON.
- JPA using transactions.

Comments:
- Libreries are decisives in order to make code working. With wrong libraries, the code does not work. Giving with error messages with very few information, and sometimes, not giving error messages.
- Tomcat v8 is not compatible with v9 and followings, some javax... packetes are not used anymore, replaced by jakarta... packets (no error messages if libraries mix that usage, but does not work)
- Database used: MySQL
