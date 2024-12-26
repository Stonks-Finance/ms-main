# Stonks Finance - Main Microservice
![](./readme_images/stonks-image.jpg)

`ms-main` is the API Gateway microservice for the **Stonks Finance** project. It acts as a centralized interface for handling requests from the frontend and routing them to the appropriate backend services. Currently, `ms-main` forwards requests to the **AI** microservice, which fetches and analyzes stock data, but it is designed with scalability in mind, allowing seamless integration of additional microservices in the future.

## Features
- **Centralized API Gateway**: Single microservice for the frontend to communicate with backend services.
- **Dynamic Routing**: Routes requests to the appropriate microservices, such as the AI microservice.
- **Scalability**: Supports future expansion to integrate with other stock market services.
- **Spring Boot Implementation**: Leverages the robust Spring Boot framework for routing and middleware functionality.

## Architecture
The `ms-main` service is part of the **Stonks Finance** architecture, which includes:
- **Frontend**: ReactJS-based UI.
- **AI Microservice**: Python FastAPI service for stock data analysis and prediction.

## Getting Started
### Prerequisites
Ensure the following are installed on your machine:
- **Java 17** or later
- **Docker** 

### Clone the Repository
```bash
git clone https://github.com/Stonks-Finance/ms-main.git
cd ms-main
```


### Build and Run

#### Locally
Simply run the following command in the application directory : 
```bash
./gradlew build bootRun
```

#### Using Docker
1. Build the Docker image:
   ```bash
   docker build -t stonks-ms-main .
   ```
2. Run the container:
   ```bash
   docker run -p 8080:8080 --name stonks-ms-main stonks-ms-main
   ```

After running application, you can just go to `http://localhost:8080/docs` to meet the API Documentation of it. 

## CI/CD Part
This project includes CI/CD also. It is continuously deploying to the **Render**, using **Github Actions**. If you want to see URL of this microservice, you can just go to <a href="https://ms-main.onrender.com/">https://ms-main.onrender.com/</a>. 

But you should consider one thing, that this project uses Render freely. And because of that, the requests can delay 50 seconds or more. It is something about Render Policy. It says : 

<i>
Free instances spin down after periods of inactivity. They do not support SSH access, scaling, one-off jobs, or persistent disks. Select any paid instance type to enable these features.
</i>

## Contributing
Contributions are welcome! Follow these steps to contribute:
* Fork the project.
* Create a new branch: `git checkout -b feature/your-feature`. 
* Add your new features.
* Submit a pull request. 

# Thanks for your attention! 