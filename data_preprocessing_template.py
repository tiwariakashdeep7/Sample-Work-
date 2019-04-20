# -*- coding: utf-8 -*-
"""
Created on Thu Dec  6 19:46:06 2018

@author: AlfaCharlie
"""

#importing libraries
import numpy as np
import matplotlib.pyplot as plt
import pandas as pd

#Importing DataSet
dataset = pd.read_csv('Data.csv')
X = dataset.iloc[:, :-1].values
Y = dataset.iloc[:, 3].values

#Splitting the dataset into Training set and Test Set
from sklearn.model_selection import train_test_split
X_train, X_test, Y_train, Y_test = train_test_split(X, Y,  test_size = 0.2, random_state = 0)

#Feature Scaling Dataset
"""from sklearn.preprocessing import StandardScaler
sc_X = StandardScaler()
X_train = sc_X.fit_transform(X_train)
X_test = sc_X.transform(X_test)"""







