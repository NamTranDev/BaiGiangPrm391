package nam.tran.baigiangprm391.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import nam.tran.baigiangprm391.R;

public class CountryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<Country> countries;
    public boolean typeHeader = false;

    public CountryAdapter(List<Country> countries) {
        this.countries = countries;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        if (viewType == 1) {
            return new HeaderViewHolder(layoutInflater.inflate(R.layout.header_item, parent, false));
        }
        return new FlagViewHolder(layoutInflater.inflate(R.layout.country_item, parent, false));
    }

    @Override
    public int getItemViewType(int position) {
        if (typeHeader) {
            if (position == 0) {
                return 1;
            } else {
                return 2;
            }
        } else {
            return 2;
        }
    }

    @Override
    public int getItemCount() {
        return countries.size() + (typeHeader ? 1 : 0);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        try{
            if (viewHolder instanceof FlagViewHolder) {
                ((FlagViewHolder) viewHolder).bind(countries.get(position));
            }
        }catch (Exception ignore){}
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {

        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public static class FlagViewHolder extends RecyclerView.ViewHolder {
        private final ImageView flag;

        public FlagViewHolder(View itemView) {
            super(itemView);
            flag = itemView.findViewById(R.id.countryFlag);
        }

        public void bind(Country country) {
            flag.setImageResource(country.getFlagImageResource());
        }
    }
}

