package com.example.flexihub.test.testapp.fragments;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.flexihub.test.testapp.R;
import com.example.flexihub.test.testapp.adapters.BlockLinesAdapter;
import com.example.flexihub.test.testapp.models.Line;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roman Zahorui on 27.10.2016.
 */

public class BlockFragment extends Fragment {

    private static final String TITLE_TAG = "TITLE_TAG";
    private static final String LINES_TAG = "LINES_TAG";

    private Toolbar toolbar;
    private ListView list;
    private boolean isActionShown;

    public static BlockFragment newInstance(String title, List<Line> lines) {

        Bundle args = new Bundle();
        args.putString(TITLE_TAG, title);

        Parcelable [] parcLines = new Parcelable[lines.size()];
        for (int i = 0; i < lines.size(); i ++) {
            parcLines[i] = lines.get(i);
        }
        args.putParcelableArray(LINES_TAG, parcLines);
        BlockFragment fragment = new BlockFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.block_fragment, null);

        toolbar = (Toolbar) rootView.findViewById(R.id.fragment_toolbar);
        toolbar.inflateMenu(R.menu.fragment_toolbar_menu);
        toolbar.setTitle(getArguments().getString(TITLE_TAG));
        toolbar.setNavigationIcon(R.drawable.ic_win);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int id = menuItem.getItemId();

                if (id == R.id.action_add) {
                    Snackbar.make(getView(), "Adding...", Snackbar.LENGTH_SHORT).show();
                } else if (id == R.id.action_edit) {
                    Snackbar.make(getView(), "Editing...", Snackbar.LENGTH_SHORT).show();
                } else if (id == R.id.action_lock) {
                    Snackbar.make(getView(), "Locking...", Snackbar.LENGTH_SHORT).show();
                }
                return true;
            }
        });
        showOverflowMenu(false);

        list = (ListView) rootView.findViewById(R.id.list_view);
        BlockLinesAdapter adapter = new BlockLinesAdapter(this.getContext(), R.layout.line_item, new ArrayList<Line>());
        list.setAdapter(adapter);

        Parcelable [] parcLines = getArguments().getParcelableArray(LINES_TAG);
        if (null != parcLines)
            for (Parcelable line : parcLines) {
                adapter.add((Line) line);
            }

        setListeners();
        return rootView;
    }

    private void setListeners() {

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (list.getCheckedItemPosition() == position && isActionShown) {
                    showOverflowMenu(false);
                    list.setItemChecked(position, false);
                } else {
                    showOverflowMenu(true);
                    list.setItemChecked(position, true);
                }
                return true;
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (list.getCheckedItemPosition() == position) {
                    list.setItemChecked(position, false);
                } else {
                    list.setItemChecked(position, true);
                }
                showOverflowMenu(false);
            }
        });
    }

    public void showOverflowMenu(boolean showMenu){
        toolbar.getMenu().setGroupVisible(R.id.context_group, showMenu);
        if (showMenu) isActionShown = true;
        else isActionShown = false;
    }
}
