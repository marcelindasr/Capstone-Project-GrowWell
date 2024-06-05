const {
  postUserHandler,
  getAllUsersHandler,
  getUserHandler,
  updateUserHandler,
  postPredictHandler,
  getAllPredictionsHandler,
  getPredictionHandler,
  deletePredictionHandler,
} = require('./handler');

const routes = [
  {
    method: 'POST',
    path: '/users',
    handler: postUserHandler,
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
  },
];

module.exports = routes;
