package sg.edu.np.mad.mad_recyclerview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements TodoAdapter.onTaskClickListener{
    ArrayList<String> tasklist = new ArrayList<String>(Arrays.asList("Take out the trash", "drink water"));
    RecyclerView recyclerView;
    EditText addTaskEdit;
    Button addTaskBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        TodoAdapter todoAdapter = new TodoAdapter(tasklist , this);
        recyclerView.setAdapter(todoAdapter);

        addTaskEdit = findViewById(R.id.addtaskedit);
        addTaskBtn = findViewById(R.id.button);
        addTaskBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                tasklist.add(addTaskEdit.getText().toString().trim());
                showNewEntry(recyclerView , tasklist);
            }
        });

    }

    /**
     * Upon calling this method, the keyboard will retract
     * and the recyclerview will scroll to the last item
     *
     * @param rv RecyclerView for scrolling to
     * @param data ArrayList that was passed into RecyclerView
     */
    private void showNewEntry(RecyclerView rv, ArrayList data){
        //scroll to the last item of the recyclerview
        rv.scrollToPosition(data.size() - 1);

        //auto hide keyboard after entry
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(rv.getWindowToken(), 0);
    }

    @Override
    public void onTaskClick(final int position) {
        LayoutInflater inflater = getLayoutInflater();
        View dialogLayout = inflater.inflate(R.layout.deletedialog, null);


        TextView txtMessage = dialogLayout.findViewById(R.id.dialogmessage);
        txtMessage.setText(Html.fromHtml( "<div>Are you sure you want to delete<br/>"+ "<b>" + tasklist.get(position) + "?</b></div>"));

        ImageView imgTrash = dialogLayout.findViewById(R.id.dialogicon);
        imgTrash.setImageResource(R.drawable.ic_baseline_delete_outline_24);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        tasklist.remove(position);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setCancelable(false)
                .setView(dialogLayout)
                .show();
    }


}
