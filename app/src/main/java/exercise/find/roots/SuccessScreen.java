package exercise.find.roots;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SuccessScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_screen);
        TextView txt = findViewById(R.id.SuccessTextView);
        Intent intent = getIntent();
        long original_number = intent.getLongExtra("original_number", 0);
        long root1 = intent.getLongExtra("root1", 0);
        long root2 = intent.getLongExtra("root2", 0);
        long time = intent.getLongExtra("time", 0);
        String msg = "Original number: " + original_number + "\nFirst root: " + root1 +
                "\nSecond root: " + root2 + "\nCalculation Time: " + time;
        txt.setText(msg);
    }
}
