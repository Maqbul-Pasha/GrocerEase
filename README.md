# GrocerEase

GrocerEase is an Android application designed to simplify grocery management. It allows users to create, read, update, and delete grocery items, categorize them, and manage their shopping lists efficiently.

## Features

- User authentication (login and signup functionality)
- Add new grocery items with names and categories
- Categorize groceries (Fruits, Dairy, Vegetables, Meat, Beverages, etc.)
- View grocery items in a color-coded list based on categories
- Edit existing grocery items
- Delete grocery items
- Clean and intuitive user interface

## Technologies Used

- Java
- Android SDK
- Firebase Authentication
- SQLite for local data storage
- ViewModel and LiveData for MVVM architecture
- ConstraintLayout for responsive UI design

## Screenshots

![grocerease](https://github.com/user-attachments/assets/a51ca787-c71e-4edc-8c0b-152f4ec10068)

![grocerease1](https://github.com/user-attachments/assets/3904986d-53cb-466a-a0f7-ce5e5e213205)


## Getting Started

To get a local copy up and running, follow these steps:

1. Clone the repository
   ```
   git clone https://github.com/Maqbul-Pasha/GrocerEase.git
   ```
2. Open the project in Android Studio
3. Sync the project with Gradle files
4. Run the app on an emulator or physical device

## Project Structure

- `LoginActivity`: Handles user authentication
- `GroceryDetailsActivity`: Manages the display and manipulation of grocery items
- `Grocery`: Model class for grocery items
- `GroceryRepository`: Manages data operations between the ViewModel and local database
- `UserViewModel`: Manages user authentication state and operations

## Future Enhancements

- Implement cloud synchronization for grocery lists
- Add barcode scanning for quick item addition
- Introduce shopping list creation and management
- Implement recipe suggestions based on available groceries

## Contributing

Contributions are what make the open-source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

Distributed under the MIT License. See `LICENSE` for more information.

## Contact


Project Link: [https://github.com/Maqbul-Pasha/GrocerEase](https://github.com/Maqbul-Pasha/GrocerEase)

## Acknowledgements

- [Android Developers Documentation](https://developer.android.com/docs)
- [Firebase Documentation](https://firebase.google.com/docs)
- [Material Design Guidelines](https://material.io/design)
