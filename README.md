# Todoing-Rest-Api
The rest api developed for the project Todoing, a simple todo app.

## Client Repository
Find the client repo here: [Todoing-React-Client](https://github.com/umutcanbolat/Todoing-React-Client)

## Installation

You need maven and installed on your system to run the project. Cd into the project directory and run the following command to start the application. Rest API will accept requests at port 8080.

```sh
mvn spring-boot:run
```

## Database

[H2 inmemory database](https://github.com/h2database/h2database) has been used in this project. That means the data will not persist on the disk. 

No additional installation required for the database.

## Sample response JSON

- Request to http://localhost:8080/getTodoListAll enpoint will generate following data.
```json
[
  {
    "listId": 1,
    "listName": "Today's tasks",
    "items": [
      {
        "itemId": 2,
        "list": 1,
        "itemName": "Do homework",
        "itemDesc": "",
        "status": true,
        "deadline": "23-05-2018",
        "createDate": "02-10-2018",
        "dependencies": [
          
        ]
      },
      {
        "itemId": 3,
        "list": 1,
        "itemName": "Fix the computer",
        "itemDesc": "",
        "status": false,
        "deadline": "23-05-2018",
        "createDate": "02-10-2018",
        "dependencies": [
          
        ]
      },
      {
        "itemId": 4,
        "list": 1,
        "itemName": "Go out to have some fun",
        "itemDesc": "after finishing other tasks",
        "status": false,
        "deadline": "23-05-2018",
        "createDate": "02-10-2018",
        "dependencies": [
          {
            "id": 5,
            "todoItem": 4,
            "dependentTo": 3
          },
          {
            "id": 6,
            "todoItem": 4,
            "dependentTo": 2
          }
        ]
      }
    ]
  }
]
```

## Credits
Developed by [Umut Canbolat](https://github.com/umutcanbolat).

## License
This project is licensed under the GNU General Public License v3.0 (GPL 3.0) - see the [LICENSE](LICENSE) file for details