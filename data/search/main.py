from fastapi import FastAPI
from typing import Optional
from recommender import recommender_api

app = FastAPI()

@app.get("/v1/m1/")
def main():
    return {"message" : "사용자 맞춤 약초 추천"}

@app.get("/v1/m1/search")
async def recommand(herbId : Optional[int] = 0):
    result = recommender_api(herbId)
    return {"herbIds" : result}