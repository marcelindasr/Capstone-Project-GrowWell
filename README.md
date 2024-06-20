# GrowWell App - Cloud Computing

## Bangkit Product Capstone Project 2024
Bangkit Capstone Team ID : C241-PR569

Welcome to the branch repository focusing cloud computing for the GrowWell app, a Bangkit 2024 Capstone project.

## API
**[GrowWell Endpoint Documentation](https://documenter.getpostman.com/view/31500221/2sA3XV7K6Q)**

**Description:**
This API for the GrowWell app enables CRUD operations. It is built using Node.js, the Hapi framework, Cloud Firestore, Firebase, and Cloud Run. The Firebase SDK handles JWT token generation, providing various tokens, including access and refresh tokens, in the `stsTokenManager` object in the response body. The API is deployed on Cloud Run, with Firestore as the database.

## How to run this code?
### **Preparations**
1. **Create GCP Project:** 
   - Create a bucket to store the machine learning model.
   - Set up a Firestore database to store the data.
2. **Firebase Setup:**
   - Navigate to Firebase and link it to your GCP project.
   - Register your application on Firebase to generate API keys by clicking the Web icon, providing the app name, and clicking on the Register app button.
   - Enable email/password authentication: Go to the authentication settings page, click on the Sign-in method tab, then click the Email/Password provider button, toggle the Enable button, and enable the Email link feature.

### **Modify Code**
1. **Clone the Repository:**
   - Clone this branch repository.
   - Create a `.env` file in the root directory and add all the required variables based on `firebase.js` and `loadModel.js`.
2. **Service Account:**
   - Generate a service account key by heading to your Firebase console, clicking the Settings icon, selecting Project Settings, going to the Service Account tab, and clicking Generate new private key.
   - Copy the downloaded JSON file to the root directory of your project and rename it as `firebaseService.json`.
3. **Configuration:**
   - Set the variable from `loadModel.js` using the PUBLIC URL of the `model.json` that you created before in the `.env` file.
   - Fill in the Firebase configuration variables in the `.env` file based on `firebaseService.json`.
   - Update `firestore.js` with your Firestore `databaseId`.
4. **Local Testing:**
   - Install dependencies: `npm install`
   - Start the application: `npm start`
   - Test the API using Postman with the provided documentation. Don't forget to replace the URL.
5. **Deployment:**
   - Modify the `DockerFile` by adding environment variables from the `.env` file, prefixed with `ENV` (e.g., `ENV MODEL_URL=<model_url>`).
   - Initialize and authenticate GCP: `gcloud init`, `gcloud auth`
   - Deploy to Cloud Run: `gcloud run deploy --source . --port 5000`

### **Test the API**
- Replace our API URL with your own API URL in your test cases or Postman collection. Also, Ensure the data store in Firebase.

By following these steps, you'll be able to set up, modify, and deploy the GrowWell app API, ensuring that it works seamlessly with your GCP and Firebase configurations.
