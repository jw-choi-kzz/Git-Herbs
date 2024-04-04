import pandas as pd
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity
from konlpy.tag import Okt

file = "./herb_medicinal_effect.csv"
herb_data = pd.read_csv(file, encoding="CP949")

t = Okt()

# 데이터 전처리
# herb_effect_data = herb_effect_data[['herb_id','medicinal_effect']]
herb_data['medicinal_effect'] = herb_data['medicinal_effect'].fillna('') # 결측값변경

stop_words = ['는', '은', '이', '가', '하', '아', '것', '들', '의', '있', '되', '수', '보', '주', '등', '한', '에', '를', '과', '만', 'же', '으로', '또', '여', '게', '때', '래', '구', '면', '좀', '므로', '그', '다', '저', '여', '렇', '에서', '면', '있', '졌', '며', '거', '란', '듯', '까지', '바로', '안', '아니', '없', '위해', '밖에', '되', '곳']

medicinal_effect = []
for row in herb_data['medicinal_effect'] :
    content_token = t.morphs(row)
    sentence = ""
    for word in content_token : 
        if not word in stop_words : 
            sentence = sentence + " " + word
    medicinal_effect.append(sentence)
herb_data['medicinal_effect'] = medicinal_effect

# TF-IDF 
tfidf = TfidfVectorizer()
tfidf_matrix = tfidf.fit_transform(herb_data['medicinal_effect'])

# 코사인 유사도
cosine_sim = cosine_similarity(tfidf_matrix, tfidf_matrix)
cosine_sim = pd.DataFrame(cosine_sim, index = herb_data.index, columns=herb_data.index)

def recommender_api(herbId : int, cosine_sim = cosine_sim):    
    try:
        herbs_id = herb_data[herb_data["herb_id"] == herbId].index

        if(herbs_id.empty == True):
            return 0 # 관련 제품 없음
        
        all = pd.Series(0)   
        for id in range(herbs_id.size):
            herb = herbs_id[id]
            herb_item = cosine_sim[herb].drop_duplicates()
            recomm = herb_item[herb_item < 0.9].nlargest(3)
            all = pd.concat([recomm, all])

        recomm_idx = all.nlargest(3)
        recomm_item = herb_data.loc[recomm_idx.index, ['herb_id']]
        recomm_herb_id = recomm_item['herb_id'].values.tolist()

        return recomm_herb_id
    except:
        return -1   