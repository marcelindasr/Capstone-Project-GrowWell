const crypto = require('crypto');
const { 
  storeKidsData,
  getKidsData,
  getKidData,
  deleteKidsData
} = require('../config/firestore');

async function postKidsHandler(request, h) {
  const { userId } =  request.params;
  const {
    kName,
    kdateOfBirth,
    kGender,
  } = request.payload;
  const kidsId = crypto.randomUUID();

  const data = {
    "userId": userId,
    "kidsId": kidsId,
    "kName": kName,
    "kidsGender": kGender,
    "kdateOfBirth": kdateOfBirth
  }

  await storeKidsData(userId,kidsId,data);

  const response = h.response({
    status: 'success',
    message: 'kids data successfully added',
    data
  })
  response.code(201);
  return response;
}

async function getAllKidsHandler(request, h) {
  const { userId } = request.params;
  try {
    const kidsData = await getKidsData(userId);
    const response = h.response({
      status: 'success',
      data: kidsData,
    })
    response.code(200);
    return response;
  } catch(error) {
    const response = h.response({
      status: 'fail',
      message: 'Failed to access kids data',
    });
    response.code(500);
    return response;
  }
}

async function getKidHandler(request, h) {
  const { userId, kidsId } = request.params;
  try {
    const kidsData = await getKidData(userId, kidsId);
    const response = h.response({
      status: 'success',
      data: kidsData,
    })
    response.code(201);
    return response;
  } catch(error){
    const response = h.response({
      status: 'fail',
      message: 'Kids data couldn\'t be found',
    });
    response.code(404);
    return response;
  }
}

async function deleteKidsHandler(request, h){
  const{userId, kidsId} = request.params;
  const result = await deleteKidsData(userId, kidsId);
  const response = h.response({
    status: 'success',
    message: result,
  })
  response.code(200);
  return response;
}

module.exports = {
  postKidsHandler,
  getAllKidsHandler,
  getKidHandler,
  deleteKidsHandler
};
