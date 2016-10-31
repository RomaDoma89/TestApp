package com.example.flexihub.test.testapp.adapters;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.MenuItem;
import android.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.flexihub.test.testapp.R;
import com.example.flexihub.test.testapp.models.Line;

import java.util.ArrayList;

/**
 * Created by Roman Zahorui on 27.10.2016.
 */

public class BlockLinesAdapter extends ArrayAdapter<Line> {

    private ArrayList<Line> lines;
    private Context context;

    public BlockLinesAdapter(Context context, int resource, ArrayList<Line> lines) {
        super(context, resource, lines);
        this.context = context;
        this.lines = lines;
    }

    @Override
    public int getCount() {
        return lines.size();
    }

    @Override
    public Line getItem(int position) {
        return lines.get(position);
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        final LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.line_item, parent, false);
        TextView lineTitle = (TextView) rowView.findViewById(R.id.line_title);
        TextView lineAvailability = (TextView) rowView.findViewById(R.id.line_available);
        lineTitle.setText(lines.get(position).getTitle());
        lineAvailability.setText(lines.get(position).getAvailability());

        final ImageView contextImage = (ImageView) rowView.findViewById(R.id.line_context_img);
        contextImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final PopupMenu popup = new PopupMenu(context, contextImage);
                popup.getMenuInflater().inflate(R.menu.line_context_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        int i = item.getItemId();
                        if (i == R.id.second) {
                            Snackbar.make(v, "Connecting to "+item.getTitle(), Snackbar.LENGTH_SHORT).show();
                            return true;
                        } else return false;
                    }
                });
                popup.show();
            }
        });
        return rowView;
    }

    @Override
    public void add(Line line) {
        lines.add(line);
        notifyDataSetChanged();
    }
}
