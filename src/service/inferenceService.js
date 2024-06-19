const tf = require('@tensorflow/tfjs-node');
const InputError = require('./InputError');

const mean = [30.17380308928173, 0.49584707311630677, 88.6554341471572];
const std = [17.5751192207996, 0.49998481897515107, 17.300996903253004];

function scale(input, mean, std) {
  return input.map((value, index) => (value - mean[index]) / std[index]);
}

async function predictClassification(model, age, gender, height) {
  try {
    let genderInt = 1;
    if (gender === 'laki-laki'){
        genderInt = 0;
    } 

    const input_array = [age, genderInt, height]
    const input_data = scale(input_array, mean, std)

    const predict_data = tf.tensor2d([input_data])

    const prediction = model.predict(predict_data);
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
