# ToDoApp

## Setup For Development

1. Clone the Repository.
2. Install Java v.17.x, Node v.16.x, npm v.8.x.
3. (Optional )The Backend Project uses Lombok, so it is recommended to install a Lombok Plugin for your IDE.
4. to start the MariaDb Database run '''docker-compose up''' in the Root Folder of the project.
5. to start the Backend run ''':backend:bootRun''' in the Root Folder of the project.
6. to install the Frontend run '''npm install''' in the frontend directory of the project.
7. to start the Frontend run '''npm start''' in the frontend directory of the project.

You can now access the Frontend under http://localhost:4200 and the api is running under http://localhost:8080/api.

Be aware that the frontend is also reachable under http://localhost:8080, but it is not functioning without changing the cors configuration from:

todo.cors.allowed.origins=http://localhost:4200

to:

todo.cors.allowed.origins=http://localhost:8080
in the application.yml. Changed done to the frontend are then not update until you restart the backend.
