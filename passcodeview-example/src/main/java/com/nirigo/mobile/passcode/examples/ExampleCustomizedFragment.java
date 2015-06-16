package com.nirigo.mobile.passcode.examples;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nirigo.mobile.passcode.R;
import com.nirigo.mobile.passcode.other.BaseFragment;
import com.nirigo.mobile.passcode.examples.adapters.CustomPasscodeAdapter;
import com.nirigo.mobile.view.passcode.PasscodeIndicator;
import com.nirigo.mobile.view.passcode.PasscodeView;

/**
 * Created by Sicz-Mesziár János on 2015.06.16..
 *
 * Example for customization.
 */
public class ExampleCustomizedFragment extends BaseFragment{

    private PasscodeView passcodeView;
    private PasscodeIndicator passcodeIndicator;

    private CustomPasscodeAdapter customPasscodeAdapter;

    private StringBuilder yourCurrentPasscode = new StringBuilder();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflateFragmentLayout(inflater, container, R.layout.fragment_example_customized);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        passcodeView = (PasscodeView) this.view.findViewById(R.id.passcode_view);
        passcodeIndicator = (PasscodeIndicator) this.view.findViewById(R.id.passcode_indicator);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        customPasscodeAdapter = new CustomPasscodeAdapter(getActivity());

        passcodeView.setAdapter(customPasscodeAdapter);
        passcodeView.setOnItemClickListener(new PasscodeView.OnItemClickListener() {
            public void onItemClick(PasscodeView view, int position, View item, Object o) {

                if(!passcodeIndicator.isAnimationInProgress()) {

                    yourCurrentPasscode.append(o.toString());
                    passcodeIndicator.setIndicatorLevel(yourCurrentPasscode.length());

                    if (yourCurrentPasscode.length() == passcodeIndicator.getIndicatorLength()) {
                        yourCurrentPasscode = new StringBuilder();
                        passcodeIndicator.wrongPasscode();
                    }

                }

            }
        });


    }

}
