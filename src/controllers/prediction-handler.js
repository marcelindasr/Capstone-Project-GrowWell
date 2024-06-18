const crypto = require('crypto');
const { 
  storePredictData, 
  getPredictionsData,
  getPredictionData,
  deletePredictionData
} = require('../config/firestore');

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
  postPredictHandler,
  getAllPredictionsHandler,
  getPredictionHandler,
  deletePredictionHandler
};
