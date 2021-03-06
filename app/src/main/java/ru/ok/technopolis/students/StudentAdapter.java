package ru.ok.technopolis.students;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private final List<Student> students;
    private final Listener onStudentClickListener;
    private int selectPos = RecyclerView.NO_POSITION;

    public StudentAdapter(List<Student> students, Listener onStudentClickListener) {
        this.students = students;
        this.onStudentClickListener = onStudentClickListener;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.student_item, viewGroup, false);
        StudentViewHolder studentViewHolder = new StudentViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStudentClickListener.onStudentClick((Student) v.getTag());
                notifyItemChanged(selectPos);
                selectPos = studentViewHolder.getAdapterPosition();
                notifyItemChanged(selectPos);
            }
        });
        return studentViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder viewHolder, int i) {
        Student student = students.get(i);
        viewHolder.bind(student);
        viewHolder.itemView.setTag(student);
        viewHolder.itemView.setSelected(selectPos==i);

    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    static final class StudentViewHolder extends RecyclerView.ViewHolder {

        private final TextView nameTextView;
        private final TextView surnameTextView;
        private final ImageView photoImageView;


        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.name_student);
            surnameTextView = itemView.findViewById(R.id.surname_student);
            photoImageView = itemView.findViewById(R.id.photo_student);
            itemView.setSelected(true);
        }

        private void bind(@NonNull Student student) {
            nameTextView.setText(student.getFirstName());
            surnameTextView.setText(student.getSecondName());
            photoImageView.setImageResource(student.getPhoto());
        }
    }


    interface Listener {

        void onStudentClick(Student student);

    }
}
