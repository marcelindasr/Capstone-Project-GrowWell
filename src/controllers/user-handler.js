const crypto = require('crypto');
const { 
  getUsersData,
  getUserData,
  updateUserData
} = require('../config/firestore');

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
      message: 'failed to access users data',
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
    response.code(200);
    return response;
  } catch(error){
    const response = h.response({
      status: 'fail',
      message: 'User not found',
    });
    response.code(404);
    return response;
  }
}

async function updateUserHandler(request, h) {
  const { userId } = request.params;
  const { fullName, email, dateOfBirth } = request.payload;
  const updatedData = {
    fullName,
    email,
    dateOfBirth,
    updatedDate: new Date().toISOString()
  };

  try {
    await updateUserData(userId, updatedData);
    const updatedUser = await getUserData(userId);

    const response = h.response({
      status: 'success',
      message: 'User data successfully updated',
      data: updatedUser
    });
    response.code(200);
    return response;
  } catch (error) {
    const response = h.response({
      status: 'fail',
      message: 'Failed to update user data',
    });
    response.code(500);
    return response;
  }
}

module.exports = {
  getAllUsersHandler,
  getUserHandler,
  updateUserHandler
};
