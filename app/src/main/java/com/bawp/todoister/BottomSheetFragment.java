package com.bawp.todoister;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bawp.todoister.model.Task;
import com.bawp.todoister.model.TaskViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.chip.Chip;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.util.Calendar;
import java.util.Date;

public class BottomSheetFragment extends BottomSheetDialogFragment implements View.OnClickListener{
    private EditText enterTodoET;
    private ImageButton calenderBtn;
    private ImageButton priorityBtn;
    private RadioGroup priorityRadioGroup;
    private RadioButton selectedRadioBtn;
    private int selectedBtnId;
    private ImageButton saveBtn;
    private CalendarView calendarView;
    private Group calenderGroup;
    private Date dueDate;
    private Calendar calendar = Calendar.getInstance();

    public BottomSheetFragment() {
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.bottom_sheet, container, false);
        calenderGroup = view.findViewById(R.id.calendar_group);
        calendarView = view.findViewById(R.id.calendar_view);
        calenderBtn = view.findViewById(R.id.today_calendar_button);
        enterTodoET = view.findViewById(R.id.enter_todo_et);
        saveBtn = view.findViewById(R.id.save_todo_button);
        priorityBtn = view.findViewById(R.id.priority_todo_button);
        priorityRadioGroup = view.findViewById(R.id.radioGroup_priority);

        Chip todayChip = view.findViewById(R.id.today_chip);
        todayChip.setOnClickListener(this::onClick);
        Chip tomorrowChip = view.findViewById(R.id.tomorrow_chip);
        tomorrowChip.setOnClickListener(this::onClick);
        Chip nextWeekChip = view.findViewById(R.id.next_week_chip);
        nextWeekChip.setOnClickListener(this::onClick);
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        calenderBtn.setOnClickListener(view12 -> {
            calenderGroup.setVisibility(calenderGroup.getVisibility() == View.GONE
                    ? View.VISIBLE
                    : View.GONE
            );
        });
        calendarView.setOnDateChangeListener((calendarView1, year, month, dayOfMonth) -> {
            calendar.clear();
            calendar.set(year, month, dayOfMonth);
            dueDate = calendar.getTime();
        });
        saveBtn.setOnClickListener(view1 -> {
            String task = enterTodoET.getText().toString().trim();
            if(!TextUtils.isEmpty(task) && dueDate != null){
                Task myTask = new Task(task, Priority.HIGH,
                    dueDate,
                    Calendar.getInstance().getTime(), false);
                TaskViewModel.insert(myTask);
            }
        });

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if(id == R.id.today_chip){
            calendar.add(Calendar.DAY_OF_YEAR, 0);
            dueDate = calendar.getTime();
        }
        else if(id == R.id.tomorrow_chip){
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            dueDate = calendar.getTime();
        }
        else if(id == R.id.next_week_chip){
            calendar.add(Calendar.DAY_OF_YEAR, 7);
            dueDate = calendar.getTime();
        }
    }
}