const { admin } = require("../config/firebase");

async function verifyToken(request,h){
  const idToken = req.cookies.access_token;

  if (!idToken) {
    response = h.response({
      status: 'fail', 
      error: 'No token provided' 
    });
    response.code(403).takeover();
    return response;
  }

  try {
    const decodedToken = await admin.auth().verifyIdToken(idToken);
    request.auth = {
      credentials: decodedToken,
      artifacts: idToken
    };
    return h.continue;
  } catch (error) {
    console.error('Error verifying token:', error);
    response = h.response({ 
      status: 'fail',
      error: 'Unauthorized' 
    });
    response.code(403).takeover();
    return response;
  }

};

module.exports = verifyToken;