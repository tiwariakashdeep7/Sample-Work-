

dataset = read.csv('50_Startups.csv')
#dataset = dataset[, 2:3]

#Encoding the data
dataset$State = factor(dataset$State, 
                         levels = c('New York', 'California', 'Florida'),
                         labels = c(1,2,3))




#Splitting dataset training set and test set
#install.packages('caTools')
library(caTools)
set.seed(123)
split = sample.split(dataset$Profit, SplitRatio = 0.8)
training_set = subset(dataset, split == TRUE)
test_set = subset(dataset, split == FALSE)


#Feature Scaling
#training_set[, 2:3] = scale(training_set[, 2:3])
#test_set[, 2:3] = scale(test_set[, 2:3])

#Fitting multiple linear regressor
regressor = lm(formula = Profit ~ .,
               data = training_set)

#predicting the set results
Y_pred = predict(regressor, newdata = test_set)


#Buildig the optimal Backward elimination
regressor = lm(formula = Profit ~ R.D.Spend + Administration + Marketing.Spend + State,
               data = dataset)
summary(regressor)
regressor = lm(formula = Profit ~ R.D.Spend + Administration + Marketing.Spend,
               data = dataset)
summary(regressor)
regressor = lm(formula = Profit ~ R.D.Spend + Marketing.Spend,
               data = dataset)
summary(regressor)
regressor = lm(formula = Profit ~ R.D.Spend,
               data = dataset)
summary(regressor)




