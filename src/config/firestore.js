const { Firestore } = require('@google-cloud/firestore');

async function storePredictData(userId, predictionId, data) {
  const db = new Firestore({
    databaseId: 'growwell-db'
  });

  const predictCollection = db.collection('users').doc(userId).collection('prediction_results');
  return predictCollection.doc(predictionId).set(data);
}

async function getPredictionsData(userId) {
  const db = new Firestore({
    databaseId: 'growwell-db'
  });

  const snapshot = await db.collection('users').doc(userId).collection('prediction_results').get();
  return snapshot.docs.map(doc => ({ id: doc.id, ...doc.data() }));
}

async function getPredictionData(userId, predictionId) {
  const db = new Firestore({
    databaseId: 'growwell-db'
  });

  const predictionRef = db.collection('users').doc(userId).collection('prediction_results').doc(predictionId);
  const doc =  await predictionRef.get()
  if (!doc.exists){
    throw new Error('Prediction data couldn\'t be found')
  }
  return { id: doc.id, ...doc.data() };
}

async function deletePredictionData(userId, predictionId) {
  const db = new Firestore({
    databaseId: 'growwell-db'
  });

  const predictionRef = db.collection('users').doc(userId).collection('prediction_results').doc(predictionId);
  await predictionRef.delete()
  return { message: 'Prediction deleted successfully' };
}

async function storeKidsData(userId, kidsId, data) {
  const db = new Firestore({
    databaseId: 'growwell-db'
  });

  const predictCollection = db.collection('users').doc(userId).collection('kids');
  return predictCollection.doc(kidsId).set(data);
}

async function getKidsData(userId) {
  const db = new Firestore({
    databaseId: 'growwell-db'
  });

  const snapshot = await db.collection('users').doc(userId).collection('kids').get();
  return snapshot.docs.map(doc => ({ id: doc.id, ...doc.data() }));
}

async function getKidData(userId, kidsId) {
  const db = new Firestore({
    databaseId: 'growwell-db'
  });

  const predictionRef = db.collection('users').doc(userId).collection('kidss').doc(kidsId);
  const doc =  await predictionRef.get()
  if (!doc.exists){
    throw new Error('Data prediksi tidak ditemukan')
  }
  return { id: doc.id, ...doc.data() };
}

async function deleteKidsData(userId, kidsId) {
  const db = new Firestore({
    databaseId: 'growwell-db'
  });

  const predictionRef = db.collection('users').doc(userId).collection('kids').doc(kidsId);
  await predictionRef.delete()
  return { message: 'Kids data deleted successfully' };
}

async function storeUserData(userId, data) {
  const db = new Firestore({
    databaseId: 'growwell-db'
  });

  const userData = db.collection('users');
  return userData.doc(userId).set(data);
}

async function getUsersData() {
  const db = new Firestore({
    databaseId: 'growwell-db'
  });

  const snapshot = await db.collection('users').get();
  return snapshot.docs.map(doc => ({ id: doc.id, ...doc.data() }));
}

async function getUserData(userId) {
  const db = new Firestore({
    databaseId: 'growwell-db'
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
    databaseId: 'growwell-db'
  });

  const userData = db.collection('users').doc(userId);
  return userData.update(data);
}

module.exports = {
  storePredictData, 
  getPredictionsData,
  getPredictionData,
  deletePredictionData,
  storeKidsData,
  getKidsData,
  getKidData,
  deleteKidsData,
  storeUserData,
  getUsersData,
  getUserData,
  updateUserData,
};