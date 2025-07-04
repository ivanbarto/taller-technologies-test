# Taller Technologies Test
First of all, I want to thank Taller Technologies for this great opportunity! It has been fun to develop this solution.
I hope you like the end result!

Instructions to compile the project:

1- Clone it

2- Open it with Android Studio and compile it.

## Technical Info
This app respects the principles of the Clean Architecture, so it is organized in 3 well-defined layers: Presentation, Domain, and Data Layer, built around a MVI design pattern.

![image](https://github.com/ivanbarto/kotlin-repos/assets/66323499/2923aaf4-ba7d-4b07-9c54-a89aa4b011c7)

<img width="607" alt="image" src="https://github.com/user-attachments/assets/2d55b605-513a-40ea-b5d0-a1ac2efed5d6" />


Also, the app has support for Dependency Injection pattern, using Koin library, which is very fast to implement and light-weight.

## End Result
The app is able to load a list of users. When we perform a click on any user, its complete name it's displayed on a snackbar. I'm also simulating a network call we the use of 'delay()' suspend func.
I have tried to be as fast as possible, taking care of the implementations and quality.

![test](https://github.com/user-attachments/assets/b895c7d8-ffed-4cdf-a704-964d985f1f35)

## Test
I've used two libraries: Koin-Test to test the generated dependencies, and MockK to mock objects and interactor/repository functions.

I've included tests cases for the following:
- dependecy injection modules: ensure that the modules have been correctly declared, and that the dependencies exist.
- domain and data layers: one test for the interactor use case, and one test for the repository get function.

![image](https://github.com/user-attachments/assets/09ef2686-3191-43aa-96dc-aa6aa1829358)

![image](https://github.com/user-attachments/assets/50b3226b-30bc-4b69-96b8-706dbe35d4b2)




