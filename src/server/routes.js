const {
  postPredictHandler,
  getAllPredictionsHandler,
  getPredictionHandler,
  deletePredictionHandler,
} = require('../controllers/prediction-handler');

const {
  postKidsHandler,
  getAllKidsHandler,
  getKidHandler,
  deleteKidsHandler
} = require('../controllers/kids-handler');

const {
  getAllUsersHandler,
  getUserHandler,
  updateUserHandler,
} = require('../controllers/user-handler');

const {
  registerUser,
  loginUser,
  logoutUser,
  resetPassword
} = require('../controllers/firebase-auth-handler')

const routes = [
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
    path: '/users/{userId}/kids',
    handler: postKidsHandler,
  },
  {
    method: 'GET',
    path: '/users/{userId}/kids',
    handler: getAllKidsHandler,
  },
  {
    method: 'GET',
    path: '/users/{userId}/kids/{kidsId}',
    handler: getKidHandler,
  },
  {
    method: 'DELETE',
    path: '/users/{userId}/kids/{kidsId}',
    handler: deleteKidsHandler,
  },
  {
    method: 'POST',
    path: '/users/{userId}/kids/{kidsId}/predictions',
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
  },
  {
    method: 'POST',
    path: '/firebase/register',
    handler: registerUser
  },
  {
    method: 'POST',
    path: '/firebase/login',
    handler: loginUser
  },
  {
    method: 'POST',
    path: '/firebase/logout',
    handler: logoutUser
  },
  {
    method: 'POST',
    path: '/firebase/reset-password',
    handler: resetPassword
  }
];

module.exports = routes;
