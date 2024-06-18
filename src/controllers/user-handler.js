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
    response.code(201);
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
  try {
    const { userId } = request.params;
    const { 
      fullName,
      email,
      dateOfBirth,
      createdDate,
    } = request.payload;
    const updatedDate = new Date().toISOString();

    const data = {
      "fullName": fullName,
      "email": email,
      "dateOfBirth": dateOfBirth,
      "createdDate": createdDate,
      "updatedDate": updatedDate,
    };

    await updateUserData(userId, data);

    const response = h.response({
      status: 'success',
      message: 'Data successfully edited'
    });
    response.code(200);
    return response;
  } catch (error) {
    console.error('Error updating user:', error);
    const response = h.response({
      status: 'error',
      message: 'Failed to update user data'
    });
    response.code(500); // Internal Server Error
    return response;
  }
}

module.exports = {
  getAllUsersHandler,
  getUserHandler,
  updateUserHandler
};
