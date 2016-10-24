package layout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.umerejaz.testapp.LLC.LLCInformation;
import com.example.umerejaz.testapp.Main.CustomAdapter;
import com.example.umerejaz.testapp.Main.Trust_information;
import com.example.umerejaz.testapp.R;
import com.example.umerejaz.testapp.SolePropreitorship.BussinessInformation;
import com.example.umerejaz.testapp.SolePropreitorship.CreateAccount;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Choose_Entity_Type.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Choose_Entity_Type#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Choose_Entity_Type extends Fragment implements View.OnClickListener {

    ListView mainListView;
    TextView maintittle,sole_propreitor,llc,trust,estate_diseased,non_profit,partnership,
            corporation,s_corporation,personal_service_corporation,
            church_controlled;

    SharedPreferences pref;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Choose_Entity_Type() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Choose_Entity_Type.
     */
    // TODO: Rename and change types and number of parameters
    public static Choose_Entity_Type newInstance(String param1, String param2) {
        Choose_Entity_Type fragment = new Choose_Entity_Type();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onActivityCreated(Bundle bundle){
    super.onActivityCreated(bundle);


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.fragment_choose__entity__type, container, false);
        maintittle=(TextView) rootView.findViewById(R.id.tv_title);
        sole_propreitor = (TextView) rootView.findViewById(R.id.sole_propreitor);
        llc = (TextView) rootView.findViewById(R.id.llc);
        trust = (TextView) rootView.findViewById(R.id.trust);
        estate_diseased = (TextView) rootView.findViewById(R.id.estate_diseased);
        non_profit = (TextView) rootView.findViewById(R.id.non_profit);
        partnership = (TextView) rootView.findViewById(R.id.partnership);
        corporation = (TextView) rootView.findViewById(R.id.corporation);
        s_corporation = (TextView) rootView.findViewById(R.id.s_corporation);
        personal_service_corporation = (TextView) rootView.findViewById(R.id.personal_service_corporation);
        church_controlled = (TextView) rootView.findViewById(R.id.church_controlled);

        sole_propreitor.setOnClickListener(this);
        llc.setOnClickListener(this);
        trust.setOnClickListener(this);
        estate_diseased.setOnClickListener(this);
        non_profit.setOnClickListener(this);
        partnership.setOnClickListener(this);
        corporation.setOnClickListener(this);
        s_corporation.setOnClickListener(this);
        personal_service_corporation.setOnClickListener(this);
        church_controlled.setOnClickListener(this);

return rootView;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == sole_propreitor.getId())
        {

            if(checkIfUserExist() == true)
            {

                Intent intent = new Intent(getActivity(), BussinessInformation.class);
                intent.putExtra("tittle", "Sole Propretior");
                startActivity(intent);
            }
            else
            {
                Intent intent = new Intent(getActivity(), CreateAccount.class);
                intent.putExtra("tittle", "Sole Propretior");
                startActivity(intent);
            }




        }

        if(view.getId() == llc.getId())
        {

            if(checkIfUserExist() == true)
            {

                Intent intent = new Intent(getActivity(), LLCInformation.class);
                intent.putExtra("tittle", "Limited Liability Company");
                startActivity(intent);
            }

            else
            {
                Intent intent = new Intent(getActivity(), CreateAccount.class);
                intent.putExtra("tittle", "Limited Liability Company");
                startActivity(intent);
            }


        }

        if(view.getId() == trust.getId())
        {


            if(checkIfUserExist() == true)
            {

                Intent intent = new Intent(getActivity(), Trust_information.class);
                intent.putExtra("tittle", "Trust");
                startActivity(intent);
            }

            else
            {
                Intent intent = new Intent(getActivity(), CreateAccount.class);

                intent.putExtra("tittle", "Trust");
                startActivity(intent);
            }


        }

        if(view.getId() == estate_diseased.getId())
        {
            if(checkIfUserExist() == true)
            {
                Intent intent = new Intent(getActivity(), CreateAccount.class);
                intent.putExtra("tittle", "Estate of Deceased Individual");
                startActivity(intent);
            }


            else
            {
                Intent intent = new Intent(getActivity(), CreateAccount.class);
                intent.putExtra("tittle", "Estate of Deceased Individual");
                startActivity(intent);
            }


        }

        if(view.getId() == non_profit.getId())
        {

            if(checkIfUserExist() == true)
            {

                Intent intent = new Intent(getActivity(), BussinessInformation.class);
                intent.putExtra("tittle", "Non Profit");
                startActivity(intent);
            }

            else
            {
                Intent intent = new Intent(getActivity(), CreateAccount.class);

                intent.putExtra("tittle", "Non Profit");
                startActivity(intent);
            }


        }

        if(view.getId() == partnership.getId())
        {

            if(checkIfUserExist() == true)
            {

                Intent intent = new Intent(getActivity(), BussinessInformation.class);
                intent.putExtra("tittle", "Partnership");
                startActivity(intent);
            }

            else
            {
                Intent intent = new Intent(getActivity(), CreateAccount.class);
                intent.putExtra("tittle", "Partnership");
                startActivity(intent);

            }


        }

        if(view.getId() == corporation.getId())
        {

            if(checkIfUserExist() == true)
            {

                Intent intent = new Intent(getActivity(), BussinessInformation.class);
                intent.putExtra("tittle", "Corporation");
                startActivity(intent);
            }
            else
            {
                Intent intent = new Intent(getActivity(), CreateAccount.class);
                intent.putExtra("tittle", "Corporation");
                startActivity(intent);
            }



        }

        if(view.getId() == s_corporation.getId())
        {

            if(checkIfUserExist() == true)
            {

                Intent intent = new Intent(getActivity(), BussinessInformation.class);
                intent.putExtra("tittle", "S-Corporation");
                startActivity(intent);
            }

            else
            {
                Intent intent = new Intent(getActivity(), CreateAccount.class);
                intent.putExtra("tittle", "S-Corporation");

                startActivity(intent);
            }



        }

        if(view.getId() == personal_service_corporation.getId())
        {

            if(checkIfUserExist() == true)
            {

                Intent intent = new Intent(getActivity(), BussinessInformation.class);
                intent.putExtra("tittle", "Personal Service Corporation");
                startActivity(intent);
            }

            else
            {
                Intent intent = new Intent(getActivity(), CreateAccount.class);
                intent.putExtra("tittle", "Personal Service Corporation");
                startActivity(intent);
            }


        }


        if(view.getId() == church_controlled.getId())
        {

            if(checkIfUserExist() == true)
            {

                Intent intent = new Intent(getActivity(), BussinessInformation.class);
                intent.putExtra("tittle", "Church Controlled");
                startActivity(intent);
            }
            else
            {
                Intent intent = new Intent(getActivity(), CreateAccount.class);

                intent.putExtra("tittle", "Church Controlled");
                startActivity(intent);
            }



        }

    }

    private boolean checkIfUserExist() {
        Boolean flag = true;
        pref = getContext().getSharedPreferences(getString(R.string.userSession), 0); // 0 - for private mode
        String name = pref.getString(getString(R.string.userName), null);
        String pass = pref.getString(getString(R.string.userPass), null);
        if (name == null || pass == null) {
            flag = false;
        }
        return flag;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
