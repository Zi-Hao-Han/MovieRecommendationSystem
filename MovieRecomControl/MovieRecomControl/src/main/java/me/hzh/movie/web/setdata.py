
import pandas as pd
import random 
import os
import numpy as np 
import matplotlib as plt
from sklearn.decomposition import PCA 
import copy
from os.path import join

WorkDir = r"/Users/hongfei/Desktop/Senior Design/Python Code"
os.chdir(WorkDir) 

def passdata(sourcedata): 


    MoviesData = pd.read_csv('movies.csv', sep='^', header=None)
    user = "userid:1001#searchcontent:type:com#searchresult:1,3,11,12,18,19,20,21,101,102,104,107,112,118,119,122,125,129,135,141,144,145,153,156,157,166,171,174,176,178,180,183,186,187,189,194,195,203,205,216,218,223,224,228,231,232,234,235,236,237,239,243,248,250,252,255,256,258,267,272,274,275,276,278,281,287,289,294,295,296,1485,1490,1500,1503,1513,1516,1517,1526,1527,1529,1532,1537,1539,1541,1542,1546,1550,1564,1566,1569,1571,1580,1581,1588,1592,1593,1594,1602,1604,1612,1614,1629,1632,1639,1640,1641,1642,1646,1648,1657,1663,1665,1682,1689,1699,1701,1702,1703,1707,1713,1717,1731,1732,1734,1739,1746,1747,1753,1755,1760,1772,1777,1784,1794,2041,2042,2044,2049,2050,2051,2052,2053,2054,2055,2059,2060,2065,2072,2073,2078,2080,2081,2082,2083,2088,2095,2098,2100,2102,2108,2109,2110,2111,2123,2124,2125,2128,2133,2134,2136,2141,2144,2907,2913,2915,2918,2923,2924,2926,2935,2936,2937,2946,2953,2961,2962,2968,2973,2975,2977,2978,2987,2997,3003,3004,3013,3018,3022,113225,113252,113348,113453,113532,113829,114028,114082,114122,114265,114552,114601,114670,114762,114818,115122,115231,115502,115617,115877,116413,116419,116799,116849,116897,116939,116977,116985,117123,117511,117590,117851,118082,118105,#favorite:1-4,158-1,274-1,294-1,26574-1,30883-1,103210-1,2-1"
    
    user_feature = user.split('#')
    
    
    movieID = random.choices(MoviesData[0], k=10)
    
    Return_MovieID = "movieid:" 
    
    for movie in movieID:
        Return_MovieID = Return_MovieID + ''.join(str(movie)) + ','
        
        
        
    Return_MovieID = Return_MovieID[:-1]
    print("python:"+sourcedata)
    
    
    
    
    
def compute_user_projection(user):
    
    ata1 = pd.read_csv('movies.csv', sep='^', header=None)
    time = np.array(data1['Date'])
    d1 = np.array(data1['WACM'])
    d2 = np.array(data1['WALC'])
    
    idxS = []
    idxE = []
    
    if len(idxS) == 0 or len(idxE)==0:
        print('Error!')
    else:
        req_time = time[idxS[0]:idxE[0]+1]
        req_d1 = d1[idxS[0]:idxE[0]+1]
        req_d2 = d2[idxS[0]:idxE[0]+1]
        
        data_save = np.vstack([req_time,req_d1,req_d2])
        data_save = data_save.T
        
        df = pd.DataFrame(data_save, columns=['Date','WACM','WALC'])
        
        df.to_csv(join(WorkDir,'2014-2018.csv'),index=False)
        
        
        
        
        
def compute_movie_projection(movie):
    
    ata1 = pd.read_csv('movies.csv', sep='^', header=None)

    idxS = []
    idxE = []
    time = []
    d1 = 0
    d2 = 0
    if len(idxS) == 0 or len(idxE)==0:
        print('Error!')
    else:
        req_time = time[idxS[0]:idxE[0]+1]
        req_d1 = d1[idxS[0]:idxE[0]+1]
        req_d2 = d2[idxS[0]:idxE[0]+1]
        

        

def plot_loss(history):
    
    plt.plot(history.history['loss'], label='loss')
    plt.plot(history.history['val_loss'], label='val_loss')
    # plt.ylim([0, 10])
    plt.xlabel('Epoch')
    plt.ylabel('Error [MPG]')
    plt.legend()
    plt.grid(True)
        

def Bt_scaler(Bt_vector, Bt_min, Bt_max):
    return (Bt_vector - Bt_min)/(Bt_max - Bt_min)

def Bt_scaler_recover(Bt_vector_scaler, Bt_min, Bt_max):
    return Bt_vector_scaler * (Bt_max - Bt_min) + Bt_min



    


        
#%%
if 1:
    data1 = pd.read_csv('movies.csv', sep='^', header=None)
    
    date = 'Date'
    ns_lis = []
    
    
    ns_idx = []
    date_idx = []
    for ns in ns_lis:
        
        idx = data1.columns.get_loc(ns)
        ns_idx.append(idx+1)
    
    idx_lis = [13983,13956,13956]
    
    idx = idx_lis [0]
    ns_i = ns_idx[0]
    ns = ns_lis [0]
    
    # all_ans = []
    i =0
    for idx,ns,ns_i in zip(idx_lis,ns_lis,ns_idx):
    
        ans = np.array(data1.loc[idx:,[date,ns]])
        est = np.array(data1.iloc[:idx,[date_idx,ns_i]])
        final1 = np.vstack([est,ans])
        if i == 0:
            backup = copy.copy(final1)
        else:
            backup = np.append(backup,final1,axis=1)
        
        #-------------------------------------------
        
        ans = np.array(data1.loc[idx:,ns])
        est = np.array(data1.iloc[:idx,ns_i])
        
        final = np.array([np.hstack([est,ans])])
        if i == 0:
            all_ans = copy.copy(final)
        else:
            all_ans = np.append(all_ans,final,axis=0)
        i+=1
    
    all_ans = np.array(all_ans.T,dtype=np.float32)
    
    ans =np.array([np.sum(all_ans,axis=1)])
    
    final_ans = np.vstack([[backup[:,0]],ans])
    final_ans = final_ans.T
    
    df = pd.DataFrame(final_ans,columns=['Date','SC'])
    

    
#%%
if 0:
    data1 = pd.read_csv('movies.csv', sep='^', header=None)
    
    times_lis = []
    date = np.array(data1['Date'])
    
    i = 0
    for itimes in times_lis[1:]:
        idxS = np.where(date == times_lis[i])[0]
        idxE = np.where(date == itimes)[0]
        if len(idxS) == 0 or len(idxE)==0:
            print('Error!')
        else:
            data2 = data1.iloc[idxS[0]:idxE[0],:]
            df = pd.DataFrame(data2)
        i+=1
        
        
        
        