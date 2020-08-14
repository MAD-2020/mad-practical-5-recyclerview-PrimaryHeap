package sg.edu.np.mad.mad_recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewholder>{
    private ArrayList<String> Tasklist;
    private onTaskClickListener  taskClickListener;

    public TodoAdapter(ArrayList<String> taskList , onTaskClickListener taskClickListener) {
        Tasklist = taskList;
        this.taskClickListener = taskClickListener;
    }


    @NonNull
    @Override
    public TodoViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.taskrow, parent , false);
        return new TodoViewholder(view , taskClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewholder holder, int position) {
        holder.taskText.setText(Tasklist.get(position));

    }

    @Override
    public int getItemCount() {
        return Tasklist.size();
    }

    public class TodoViewholder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView taskText;
        onTaskClickListener taskClickListener;
        public TodoViewholder(@NonNull View itemView , onTaskClickListener taskClickListener) {
            super(itemView);
            this.taskText = itemView.findViewById(R.id.textView);
            this.taskClickListener = taskClickListener;
            taskText.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            taskClickListener.onTaskClick(getAdapterPosition());
        }
    }
    public  interface onTaskClickListener{
        void  onTaskClick(int position);
    }
}
