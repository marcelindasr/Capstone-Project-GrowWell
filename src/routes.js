const {
  registerUser,
  loginUser,
  logoutUser,
  resetPassword,
} = require('./controllers/firebase-auth-controller'); 

const {
  getAllUsersHandler,
  getUserHandler,
  updateUserHandler,
  postPredictHandler,
  getAllPredictionsHandler,
  getPredictionHandler,
  deletePredictionHandler,
} = require('./controllers/handler');

const routes = [
  {
    method: 'POST',
    path: '/api/register',
    handler: registerUser,
  },
  {
    method: 'POST',
    path: '/api/login',
    handler: loginUser,
  },
  {
    method: 'POST',
    path: '/api/logout',
    handler: logoutUser,
  },
  {
    method: 'POST',
    path: '/api/reset-password',
    handler: resetPassword,
  },
  {
    method: 'GET',
    path: '/users',
    handler: getAllUsersHandler,
  },
  {
    method: 'GET',
    path: '/users/{userId}',
    handler: getUserHandler,
  },
  {
    method: 'PUT',
    path: '/users/{userId}',
    handler: updateUserHandler,
  },
  {
    method: 'POST',
    path: '/users/{userId}/predictions',
    handler: postPredictHandler,
  },
  {
    method: 'GET',
    path: '/users/{userId}/predictions',
    handler: getAllPredictionsHandler,
  },
  {
    method: 'GET',
    path: '/users/{userId}/predictions/{predictionId}',
    handler: getPredictionHandler,
  },
  {
    method: 'DELETE',
    path: '/users/{userId}/predictions/{predictionId}',
    handler: deletePredictionHandler,
  }
];

module.exports = routes;
