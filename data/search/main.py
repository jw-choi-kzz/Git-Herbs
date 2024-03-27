from fastapi import FastAPI
from recommender import recommender_api

app = FastAPI()

@app.get("/v1/m1/")
def main():
    return {"message" : "사용자 맞춤 약초 추천"}

@app.get("/v1/m1/search/{herbId}")
def recommand(herbId : int):
    result = recommender_api(herbId)
    return {"result" : result}