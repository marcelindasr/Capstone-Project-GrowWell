const { Firestore } = require('@google-cloud/firestore');

async function storePredictData(userId, predictionId, data) {
  const db = new Firestore({
    databaseId: 'test-growwell'
  });

  const predictCollection = db.collection('users').doc(userId).collection('prediction_results');
  return predictCollection.doc(predictionId).set(data);
}

async function getPredictionsData(userId) {
  const db = new Firestore({
    databaseId: 'test-growwell'
  });

  const snapshot = await db.collection('users').doc(userId).collection('prediction_results').get();
  return snapshot.docs.map(doc => ({ id: doc.id, ...doc.data() }));
}

async function getPredictionData(userId, predictionId) {
  const db = new Firestore({
    databaseId: 'test-growwell'
  });

  const predictionRef = db.collection('users').doc(userId).collection('prediction_results').doc(predictionId);
  const doc =  await predictionRef.get()
  if (!doc.exists){
    throw new Error('Data prediksi tidak ditemukan')
  }
  return { id: doc.id, ...doc.data() };
}

async function deletePredictionData(userId, predictionId) {
  const db = new Firestore({
    databaseId: 'test-growwell'
  });

  const predictionRef = db.collection('users').doc(userId).collection('prediction_results').doc(predictionId);
  await predictionRef.delete()
  return { message: 'Prediction deleted successfully' };
}

async function storeUserData(userId, data) {
  const db = new Firestore({
    databaseId: 'test-growwell'
  });

  const userData = db.collection('users');
  return userData.doc(userId).set(data);
}

async function getUsersData() {
  const db = new Firestore({
    databaseId: 'test-growwell'
  });

  const snapshot = await db.collection('users').get();
  return snapshot.docs.map(doc => ({ id: doc.id, ...doc.data() }));
}

async function getUserData(userId) {
  const db = new Firestore({
    databaseId: 'test-growwell'
  });

  const userRef = db.collection('users').doc(userId);
  const doc =  await userRef.get()
  if (!doc.exists){
    throw new Error('Data user tidak ditemukan')
  }
  return { id: doc.id, ...doc.data() };
}

async function updateUserData(userId, data) {
  const db = new Firestore({
    databaseId: 'test-growwell'
  });

  const userData = db.collection('users').doc(userId);
  return userData.update(data);
}

module.exports = {
  storePredictData, 
  getPredictionsData,
  getPredictionData,
  deletePredictionData,
  storeUserData,
  getUsersData,
  getUserData,
  updateUserData,
};
