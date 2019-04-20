#importing Libraries

dataset = read.csv('Position_Salaries.csv')
dataset = dataset[2:3]

#Splitting dataset training set and test set
#install.packages('caTools')
#library(caTools)
#set.seed(123)
#split = sample.split(dataset$Purchased, SplitRatio = 0.8)
#training_set = subset(dataset, split == TRUE)
#test_set = subset(dataset, split == FALSE)

#fitting linear regression to the dataset
lin_reg = lm(formula = Salary ~ .,
             data = dataset)

#fitting polynomial regression to the dataset
dataset$Level2 = dataset$Level^2
dataset$Level3 = dataset$Level^3
dataset$Level4 = dataset$Level^4
dataset$Level5 = dataset$Level^5
poly_reg = lm(formula = Salary ~ .,
              data =  dataset)

#Visualizing the Linear regression results
install.packages('ggplot2')
library(ggplot2)
ggplot() +
  geom_point(aes(x = dataset$Level, y = dataset$Salary),
             color = 'red') +
  geom_line(aes(x = dataset$Level, y = predict(lin_reg, newdata = dataset)),
            color = 'blue') +
ggtitle('Truth or Bluff(Linear Regression)')+
  xlab('Level')+
  ylab('Salary')

#Visualizing the Polynomial regression results
library(ggplot2)
ggplot() +
  geom_point(aes(x = dataset$Level, y = dataset$Salary),
             color = 'red') +
  geom_line(aes(x = dataset$Level, y = predict(poly_reg, newdata = dataset)),
            color = 'blue') +
  ggtitle('Truth or Bluff(Polynomial Regression)')+
  xlab('Level')+
  ylab('Salary')

#Predicting a new result with Linear Regression
y_pred = predict(lin_reg, data.frame(Level = 6.5))

#Predicting a new result with Polynmial Regression
y_pred = predict(poly_reg, data.frame(Level = 6.5,
                                      Level2 = 6.5^2,
                                      Level3 = 6.5^3,
                                      Level4 = 6.5^4,
                                      Level5 = 6.5^5))


#Feature Scaling
#training_set[, 2:3] = scale(training_set[, 2:3])
#test_set[, 2:3] = scale(test_set[, 2:3])
