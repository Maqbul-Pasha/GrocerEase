package com.example.groceryapp.ui.grocerydetail;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.groceryapp.R;
import com.example.groceryapp.data.model.Grocery;
import com.example.groceryapp.repository.GroceryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GroceryDetailsActivity extends AppCompatActivity {
    private EditText inputName;
    private Spinner categorySpinner;
    private Button addButton, deleteButton;
    private GroceryRepository groceryRepository;
    private ArrayAdapter<Grocery> adapter;
    private List<Grocery> groceriesList;
    private ListView groceryListView;
    private boolean isUpdateMode = false;
    private String selectedGroceryId = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_details);

        inputName = findViewById(R.id.inputGroceryName);
        categorySpinner = findViewById(R.id.categorySpinner);
        addButton = findViewById(R.id.addButton);
        deleteButton = findViewById(R.id.deleteButton);
        groceryListView = findViewById(R.id.groceryListView);
        groceryRepository = new GroceryRepository(this);

        setupCategorySpinner();
        setupGroceryList();
        setupButtons();
    }

    private void setupCategorySpinner() {
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.grocery_categories, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(spinnerAdapter);
    }

    private void setupGroceryList() {
        groceriesList = new ArrayList<>();
        adapter = new ArrayAdapter<Grocery>(this, R.layout.grocery_list_item, R.id.groceryItemText, groceriesList) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                Grocery grocery = getItem(position);
                if (grocery != null) {
                    TextView textView = view.findViewById(R.id.groceryItemText);
                    textView.setText(grocery.getName()); // Set the name explicitly
                    view.setBackgroundColor(getCategoryColor(grocery.getCategory()));
                }
                return view;
            }
        };
        groceryListView.setAdapter(adapter);
        displayGroceryFromSQLite();

        groceryListView.setOnItemClickListener((parent, view, position, id) -> {
            Grocery selectedGrocery = (Grocery) parent.getItemAtPosition(position);
            inputName.setText(selectedGrocery.getName());
            int spinnerPosition = ((ArrayAdapter) categorySpinner.getAdapter()).getPosition(selectedGrocery.getCategory());
            categorySpinner.setSelection(spinnerPosition);
            selectedGroceryId = selectedGrocery.getId();
            isUpdateMode = true;
            addButton.setText("UPDATE");
        });
    }

    private int getCategoryColor(String category) {
        switch (category) {
            case "Fruits":
                return ContextCompat.getColor(this, R.color.fruits_green);
            case "Dairy":
                return ContextCompat.getColor(this, R.color.dairy_blue);
            case "Vegetables":
                return ContextCompat.getColor(this, R.color.vegetables_orange);
            case "Meat":
                return ContextCompat.getColor(this, R.color.meat_red);
            case "Beverages":
                return ContextCompat.getColor(this, R.color.beverages_purple);
            default:
                return ContextCompat.getColor(this, R.color.default_gray);
        }
    }

    private void setupButtons() {
        addButton.setOnClickListener(view -> {
            String name = inputName.getText().toString();
            String category = categorySpinner.getSelectedItem().toString();
            if (!name.isEmpty()) {
                if (!isUpdateMode) {
                    String id = UUID.randomUUID().toString();
                    Grocery newGrocery = new Grocery(id, name, category);
                    groceryRepository.saveGroceryToLocalDatabase(newGrocery);
                    Toast.makeText(GroceryDetailsActivity.this, "Grocery added successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Grocery updateGrocery = new Grocery(selectedGroceryId, name, category);
                    groceryRepository.updateGroceryInLocalDatabase(updateGrocery);
                    Toast.makeText(GroceryDetailsActivity.this, "Grocery updated successfully", Toast.LENGTH_SHORT).show();
                    isUpdateMode = false;
                    addButton.setText("Add");
                }
                inputName.setText("");
                categorySpinner.setSelection(0);
                displayGroceryFromSQLite();
            } else {
                Toast.makeText(GroceryDetailsActivity.this, "Please enter a grocery name", Toast.LENGTH_SHORT).show();
            }
        });

        deleteButton.setOnClickListener(view -> {
            if (selectedGroceryId != null) {
                groceryRepository.deleteGroceryFromLocalDB(selectedGroceryId);
                Toast.makeText(GroceryDetailsActivity.this, "Grocery Deleted", Toast.LENGTH_SHORT).show();
                displayGroceryFromSQLite();
                inputName.setText("");
                categorySpinner.setSelection(0);
                selectedGroceryId = null;
                isUpdateMode = false;
                addButton.setText("Add");
            }
        });
    }

    private void displayGroceryFromSQLite() {
        groceriesList.clear();
        groceriesList.addAll(groceryRepository.getAllGroceryFromLocalDatabase());
        adapter.notifyDataSetChanged();
    }
}