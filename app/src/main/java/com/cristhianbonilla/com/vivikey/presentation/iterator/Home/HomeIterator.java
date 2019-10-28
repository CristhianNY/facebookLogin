    package com.cristhianbonilla.com.vivikey.presentation.iterator.Home;
    import android.content.Context;

    import androidx.annotation.NonNull;
    import androidx.annotation.Nullable;

    import com.cristhianbonilla.com.vivikey.core.domain.Contact;
    import com.cristhianbonilla.com.vivikey.core.domain.ContactNumber;
    import com.cristhianbonilla.com.vivikey.core.domain.User;
    import com.cristhianbonilla.com.vivikey.presentation.iterator.BaseIterator;
    import com.cristhianbonilla.com.vivikey.presentation.presenter.Home.IFirnedsListListener;
    import com.google.firebase.database.ChildEventListener;
    import com.google.firebase.database.DataSnapshot;
    import com.google.firebase.database.DatabaseError;
    import com.google.firebase.database.DatabaseReference;
    import com.google.firebase.database.FirebaseDatabase;
    import com.google.firebase.database.ValueEventListener;

    import java.util.ArrayList;
    import java.util.List;

    public class HomeIterator extends BaseIterator implements IHomeIterator {

        private IFirnedsListListener callback;

        public void setOnHeadlineSelectedListener(IFirnedsListListener callback) {
            this.callback = callback;
        }
        @Override
        public  List<Contact> getContactsFriendsFromFirebase(Context context, ArrayList<Contact> contacts, String myPhoneNumber) {


            initFirebase(context);

            List<Contact> myFriendsList = new ArrayList<>();
            List<ContactNumber> phones = new ArrayList<>();

            for (Contact contact: contacts) {
            final FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference ref = database.getReference("contactsByUser/"+contact.getPhoneNumber()+"/"+myPhoneNumber);
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.getValue()!=null){

                            Contact friend = dataSnapshot.getValue(Contact.class);
                            phones.add(new ContactNumber(friend.getOwner(),dataSnapshot.getKey()));
                            myFriendsList.add(friend);


                        insertFriends(myPhoneNumber,phones,context);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("The read failed: " + databaseError.getCode());
                  }
                });
            }

            return  getFriendsByPhoneNumber(context,myPhoneNumber);

        }

        @Override
        public List<Contact> getFriendsByPhoneNumber(Context context, String myPhoneNumber) {
            initFirebase(context);

            List<Contact> myFriendsList = new ArrayList<>();
                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference ref = database.getReference("friendsByPhoneUsers/"+myPhoneNumber);
                ref.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        ContactNumber contactNumber = dataSnapshot.getValue(ContactNumber.class);
                        getContactInfoByPhoneNumberAndOwner(context,myPhoneNumber,contactNumber.getPhoneNumber());

                        for (Contact contactInfo: getContactInfoByPhoneNumberAndOwner(context,myPhoneNumber,contactNumber.getPhoneNumber())) {
                            myFriendsList.add(contactInfo);
                        }
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            return  myFriendsList;
        }



        @Override
        public void insertContacts(User user, List<Contact> contacts, Context context) {
            initFirebase(context);
            databaseReference.getDatabase().getReference().getDatabase().getReference();
            for (Contact contact:contacts) {
                databaseReference.child("contactsByUser").child(user.getPhone()).child(contact.getPhoneNumber()).setValue(contact);
            }

        }

        @Override
        public void insertFriends(String myPhoneNumber, List<ContactNumber> contacts, Context context) {
            initFirebase(context);
            databaseReference.getDatabase().getReference().getDatabase().getReference();
            for (ContactNumber contact:contacts) {
                databaseReference.child("friendsByPhoneUsers").child(myPhoneNumber).child(contact.getPhoneNumber()).setValue(contact);
            }
        }

        @Override
        public  List<Contact>  getContactInfoByPhoneNumberAndOwner(Context context, String myPhoneNumber,String friendNumber) {
            initFirebase(context);

            List<Contact> contactInfoArray = new ArrayList<>();

            final FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference ref = database.getReference("contactsByUser/"+myPhoneNumber+"/"+friendNumber);
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                 Contact contactInfo = dataSnapshot.getValue(Contact.class);
                 contactInfoArray.add(contactInfo);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });



            return contactInfoArray;
        }
    }
