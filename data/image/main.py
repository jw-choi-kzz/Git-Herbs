from typing import Union

from fastapi import FastAPI, File, UploadFile
import pickle as pk
import tensorflow as tf
from PIL import Image
import numpy as np
import io

result = []
with open('./result.pkl','rb') as f:
    result = pk.load(f)
    assert isinstance(result, list)

model_load_path = './model'
model = tf.keras.models.load_model(model_load_path)


IMG_SIZE = (224, 224)
INTERPOLATION = 'lanczos3'


app = FastAPI()

@app.get("/")
def read_root():
    return {"Hello": "World"}


@app.get("/items/{item_id}")
def read_item(item_id: int, q: Union[str, None] = None):
    return {"item_id": item_id, "q": q}

@app.post("/img")
async def img_class(img: UploadFile):
    # uploadfile을 tensor로
    content = await img.read()
    image = Image.open(io.BytesIO(content))
    data = np.array(image)

    # 이미지 읽어오기
    image3 = tf.convert_to_tensor(data, dtype=tf.uint8)
    image3 = tf.io.decode_image(image3, channels=3)

    # 이미지 리사이즈
    image3 = tf.image.resize(image3, IMG_SIZE, method=INTERPOLATION)

    # predict에 활용하기 위한 차원 변경
    image4 = image3[tf.newaxis, ...]
    image4.shape

    test_result2 = model.predict(image4).flatten()

    test_enumerate2 = list(enumerate(test_result2))
    test_enumerate2.sort(key=lambda x: -x[1])
    test_enumerate2 = list(map(lambda x: (x[0], round(x[1], 12)), test_enumerate2))

    return convert_to_dict(test_enumerate2, result)

def convert_to_dict(test: list, result: list) -> dict:
    res = dict()
    for i in range(10):
        num = test[i][0]
        percent = test[i][1]
        res[result[num]] = round(percent*100, 2)
    return res