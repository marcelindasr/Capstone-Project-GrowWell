const tf = require('@tensorflow/tfjs-node');
const InputError = require('./InputError');

async function predictClassification(model, age, gender, height) {
  try {
    let genderInt = 0;
    if (gender === 'laki-laki'){
        genderInt = 1;
    } 

    const input_data = tf.tensor2d([[age, genderInt, height]])

    const prediction = model.predict(input_data);
    const score = await prediction.data();

    const classes = [ 'normal', 'severely stunted', 'stunted', 'tinggi' ];

    const classResult = await tf.argMax(prediction, 1).dataSync()[0];
    const label = classes[classResult];

    return label;
  } catch (error) {
    throw new InputError(`Terjadi kesalahan dalam melakukan prediksi`);
  }
}

module.exports = predictClassification;
