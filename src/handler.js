const crypto = require('crypto');
const { 
  storePredictData, 
  getPredictionsData,
  getPredictionData,
  deletePredictionData,
  storeUserData,
  getUsersData,
  getUserData,
  updateUserData,
} = require('./firestore');

async function postUserHandler(request, h) {
  const { 
    tokenFirebase,
    fullName,
    email,
    image,
  } = request.payload;
  const userId = crypto.randomUUID();
  const createdDate = new Date().toISOString();

  const data = {
    "userId": userId,
    "userToken": tokenFirebase,
    "fullName": fullName,
    "email": email,
    "image": image,
    "createdDate": createdDate,
    "updatedDate": createdDate,
  }

  await storeUserData(userId,data);

  const response = h.response({
    status: 'success',
    message: 'Data user berhasil ditambahkan'
  })
  response.code(201);
  return response;
}

async function getAllUsersHandler(request, h) {
  try {
    const usersData = await getUsersData();
    const response = h.response({
      status: 'success',
      data: usersData,
    })
    response.code(200);
    return response;
  } catch(error) {
    const response = h.response({
      status: 'fail',
      message: 'Gagal mengakses user',
    });
    response.code(500);
    return response;
  }
}

async function getUserHandler(request, h) {
  const { userId } = request.params;
  try {
    const userData = await getUserData(userId);
    const response = h.response({
      status: 'success',
      data: userData,
    })
    response.code(201);
    return response;
  } catch(error){
    const response = h.response({
      status: 'fail',
      message: 'User tidak ditemukan',
    });
    response.code(404);
    return response;
  }
}

async function updateUserHandler(request, h) {
  const { userId } = request.params;
  const { 
    userToken,
    fullName,
    email,
    image,
    createdDate,
  } = request.payload;
  const updatedDate = new Date().toISOString();

  const data = {
    "userId": userId,
    "userToken": userToken,
    "fullName": fullName,
    "email": email,
    "image": image,
    "createdDate": createdDate,
    "updatedDate": updatedDate,
  }

  await updateUserData(userId,data);

  const response = h.response({
    status: 'success',
    message: 'Data user berhasil diperbarui'
  })
  response.code(200);
  return response;
}

async function postPredictHandler(request, h) {
  const { userId } =  request.params;
  const {
    age,
    height,
    predictionResult,
    suggestion,
  } = request.payload;
  const predictionId = crypto.randomUUID();
  const predictionDate = new Date().toISOString();

  const data = {
    "userId": userId,
    "predictionId": predictionId,
    "age": age,
    "height": height,
    "predictionResult": predictionResult,
    "suggestion": suggestion,
    "predictionDate": predictionDate
  }

  await storePredictData(userId,predictionId,data);

  const response = h.response({
    status: 'success',
    message: 'Data prediksi berhasil ditambahkan'
  })
  response.code(201);
  return response;
}

async function getAllPredictionsHandler(request, h) {
  const { userId } = request.params;
  try {
    const predictionsData = await getPredictionsData(userId);
    const response = h.response({
      status: 'success',
      data: predictionsData,
    })
    response.code(200);
    return response;
  } catch(error) {
    const response = h.response({
      status: 'fail',
      message: 'Gagal mengakses hasil prediksi',
    });
    response.code(500);
    return response;
  }
}

async function getPredictionHandler(request, h) {
  const { userId, predictionId } = request.params;
  try {
    const predictionData = await getPredictionData(userId, predictionId);
    const response = h.response({
      status: 'success',
      data: predictionData,
    })
    response.code(201);
    return response;
  } catch(error){
    const response = h.response({
      status: 'fail',
      message: 'Hasil prediksi tidak ditemukan',
    });
    response.code(404);
    return response;
  }
}

async function deletePredictionHandler(request, h){
  const{userId, predictionId} = request.params;
  const result = await deletePredictionData(userId, predictionId);
  const response = h.response({
    status: 'success',
    message: result,
  })
  response.code(200);
  return response;
}

module.exports = {
  postUserHandler,
  getAllUsersHandler,
  getUserHandler,
  updateUserHandler,
  postPredictHandler,
  getAllPredictionsHandler,
  getPredictionHandler,
  deletePredictionHandler,
};
