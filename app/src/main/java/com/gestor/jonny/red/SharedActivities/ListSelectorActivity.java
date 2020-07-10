package com.gestor.jonny.red.SharedActivities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.gestor.jonny.red.R;
import com.gestor.jonny.red.SharedActivities.Adapter.ItemSelectorAdapter;
import com.gestor.jonny.red.SharedActivities.Models.ListSelectorModel;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ListSelectorActivity extends AppCompatActivity {
    @BindView(R.id.list_selector) ListView listSelector;

    private ArrayList<ListSelectorModel> options;
    private ArrayList<ListSelectorModel> selectedOptions = new ArrayList<>();
    private boolean isMultiSelection = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_selector_layout);
        ButterKnife.bind(this);
        getListIntent();
        setList();
    }

    @Override
    public void onBackPressed() {
        if (isMultiSelection && selectedOptions.size() > 0) {
            Intent intent = getIntent();
            intent.putExtra("option", selectedOptions);
            setResult(RESULT_OK, intent);
        }

        super.onBackPressed();
    }

    private void getListIntent() {
        options = (ArrayList<ListSelectorModel>) getIntent().getSerializableExtra("options");
        isMultiSelection = getIntent().getBooleanExtra("isMultiSelection", false);
        if (isMultiSelection) {
            listSelector.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        }
    }

    private void setList() {
        ItemSelectorAdapter adapter = new ItemSelectorAdapter(this, options);
        listSelector.setAdapter(adapter);
        listSelector.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (!isMultiSelection) {
                    Intent intent = getIntent();
                    intent.putExtra("option", options.get(i));
                    setResult(RESULT_OK, intent);
                    ListSelectorActivity.super.onBackPressed();
                } else {
                    if (selectedOptions.contains(options.get(i))) {
                        selectedOptions.remove(options.get(i));
                        view.setBackgroundColor(Color.TRANSPARENT);
                    } else {
                        selectedOptions.add(options.get(i));
                        view.setBackgroundColor(Color.GRAY);
                    }
                }
            }
        });
    }
}
