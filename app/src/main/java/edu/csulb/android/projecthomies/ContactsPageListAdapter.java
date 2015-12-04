package edu.csulb.android.projecthomies;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

// ADAPTER CLASS FOR CONTACTS
public class ContactsPageListAdapter extends RecyclerView.Adapter<ContactsPageListAdapter.PersonViewHolder> {

    public static class PersonViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView personName;
        TextView personCompany;
        ImageView personPhoto;

        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.card_view);
            personName = (TextView)itemView.findViewById(R.id.person_name);
            personCompany = (TextView)itemView.findViewById(R.id.person_company);
            personPhoto = (ImageView)itemView.findViewById(R.id.person_photo);
        }
    }

    List<ContactsPageCardData> persons;

    ContactsPageListAdapter(List<ContactsPageCardData> persons){
        this.persons = persons;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_contacts_page_card_item, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        personViewHolder.personName.setText(persons.get(i).getName());
        personViewHolder.personCompany.setText(persons.get(i).getCompany());
        personViewHolder.personPhoto.setImageResource(persons.get(i).getImageID());
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }
}
