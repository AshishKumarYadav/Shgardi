Shgardi_Android_Application
Description

Displaying a list of popular people (actors, directors ..etc) with infinite scrolling.
* The ability to search for people with infinite scrolling.
* When a person entry is selected, a details view is opened with the basic personal info and grid view of his/her images.

ScreenShots:


![shgardi 1](https://github.com/user-attachments/assets/ca99cb35-3e59-41ab-803e-7fe76a909b72)  ![shgardi 2](https://github.com/user-attachments/assets/91a61606-5741-4015-9a93-81997824adb0) 


This application is created mainly by keeping Clean Code MVVM Architecture in mind.

Why the cleaner approach?

Separation of code in different layers with assigned responsibilities making it easier for further modification.
High level of abstraction
Loose coupling between the code
Testing of code is painless
What are the Different Layers of the Project?

* Data layer: Would dispense the required data for the application to the domain layer by implementing interface exposed by the domain.

* Remote Database: API provides remote networking implementation. Any networking library can be integrated into Android Application using Retrofit. Defining the interfaces and setting up the RetrofitService is done in this layer.

* Domain layer: This will be the most generic layer of the three. It will connect the presentation layer with the data layer. This is the layer where app-related business logic will be executed. All the application use-cases and the repositories interfaces reside in the domain layer.

* Use Cases: Use cases are the application logic executor. As the name depicts each functionality can have its separate use case. With more granularity of the use case creation, it can be reused more often.
* Repositories: It specifies the functionalities required by the use cases which is implemented by the data layer.

* Presentation layer: The presentation layer provides the UI implementation of the application. This layer internally implements MVVM (Model-View-ViewModel) architecture.

Why MVVM Architecture over other patterns: MVVM architecture is a Model-View-ViewModel architecture that removes the tight coupling between each component. Most importantly, in this architecture, the children don't have the direct reference to the parent, they only have the reference by observables. Also View Model store and manage UI-related data in a lifecycle conscious way. It allows data to survive configuration changes such as screen rotations.

Technologies

* Kotlin - Official programming language for Android development.
* Coroutines - For working with asynchronous threading related task.
* Flow - A cold asynchronous data stream that sequentially emits values and completes normally or with an exception.
* Android Architecture Components - Collection of libraries that help you design robust, testable, and maintainable apps. Thus they help us to separate business logic apart from the UI logic and helps us in designing proper architecture.
* LiveData - Data objects that notify views when the underlying database changes.
* ViewModel - Stores UI-related data that isn't destroyed on configuration changes.
* DataBinding - The Data Binding Library is a support library that allows you to bind UI components in your layouts to data sources in your app using a declarative format rather than programmatically.
* Dependency Injection -
* Dagger-2 - A fast dependency injector for Java and Android. Dagger is a compile-time framework for dependency injection.
* Retrofit - A type-safe HTTP client for Android and Java.
* Glide - Glide is a fast and efficient open source media management and image loading framework for Android.

Tools
* Android Studio
* TMDB API


Author
Ashish Kumar Yadav
