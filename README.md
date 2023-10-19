
# CountFacesApi

This is a Spring Boot API for detecting the number of faces in uploaded images. It uses a Python script for face detection. 

## Table of Contents
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [API Endpoints](#api-endpoints)
- [Usage](#usage)
- [Contributing](#contributing)

## Features
- Upload images for face detection.
- Detect and count the number of faces in the uploaded images.
- Supports both image file uploads and image URLs.

## Prerequisites
- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Maven](https://maven.apache.org/download.cgi) (for building the project)
- Python (required for face detection, see installation instructions below)
- Spring Boot (included in the project)

### Installing Python
Make sure Python is installed on your system. The Python script used for face detection should be compatible with your Python version.

```bash
# Check Python version
python --version
```

If Python is not installed, you can download and install it from the official [Python website](https://www.python.org/downloads/).

## Getting Started

Clone the repository to your local machine:
```
git clone https://github.com/your-username/facedetect-api.git
```

Navigate to the project directory:
```
cd CountFacesApi
```

Build the project using Maven:
```
mvn clean package -DskipTests
```

Run the Spring Boot application using your IDE.

Your API should now be up and running at http://localhost:8080.



## API Endpoints
### Upload an image for face detection (via multipart form data)
- URL: /api/v1/facedetect/get
- HTTP Method: POST
- Request Parameter: image (multipart file)
- Response: The API will return a message indicating the number of - faces detected in the image


### Upload an image for face detection (via image URL)
- URL: /api/v1/facedetect/get
- HTTP Method: GET
- Request Parameter: imagePath (image URL)
- Response: The API will return a message indicating the number of - faces detected in the image

Eg:-http://localhost:8080/api/v1/facedetect/get?imagePath={imagepath}


## Usage
Use the provided API endpoints to upload images or provide image URLs for face detection.
The API will return the count of faces detected in the image.

## Contributing
If you'd like to contribute to this project, please open an issue or create a pull request with your suggested changes. We welcome contributions.

