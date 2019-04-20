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


#fitting the SVR to the dataset
#Create your regressor
install.packages('e1071')
library(e1071)
regressor = svm(formula = Salary ~ .,
                data = dataset,
                type = 'eps-regression')

#Predicting a new result 
y_pred = predict(regressor, data.frame(Level = 6.5))

#Visualizing the SVR results(For Higher Resolution and smoother curve)
library(ggplot2)
ggplot() +
  geom_point(aes(x = dataset$Level, y = dataset$Salary),
             color = 'red') +
  geom_line(aes(x = dataset$Level, y = predict(regressor, newdata = dataset)),
            color = 'blue') +
  ggtitle('Truth or Bluff(SVR)')+
  xlab('Level')+
  ylab('Salary')





