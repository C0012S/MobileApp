package com.example.mapex;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

public class MapsActivity extends FragmentActivity implements
        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;

    // 위치 정보 얻는 객체
    private FusedLocationProviderClient mFusedLocationClient;

    // 권한 체크 요청 코드 정의
    private static final int REQUEST_CODE_PERMISSIONS = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_maps);
        setContentView(R.layout.activity_maps2);

        // GoogleAPIClient의 인스턴스 생성
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // 현재 사용자 위치 표시(수동)
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        ToggleButton b_mapMode = findViewById(R.id.b_mapmode);
        b_mapMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                else
                    mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            }
        });

        ToggleButton b_showCurPos = findViewById(R.id.b_showCurPos);
        b_showCurPos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // 권한 체크
                if (ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION)
                                != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MapsActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                                    Manifest.permission.ACCESS_COARSE_LOCATION},
                            REQUEST_CODE_PERMISSIONS);
                    return;
                }

                mMap.setMyLocationEnabled(isChecked);
            }
        });
    }

    //API에 접속하고 해제하는 작업 필요
    @Override
    protected void onStart() { //구글 API Client에 접속하는 일 수행
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() { //구글 API Client로부터 연결 해제하는 작업 수행
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        // 마커 추가
        LatLng kb = new LatLng(37.579787, 126.977030 ); //경복궁 위도, 경도
        mMap.addMarker(new MarkerOptions().position(kb).title("경복궁"));

        /*/
        // 카메라 이동
        mMap.moveCamera(CameraUpdateFactory.newLatLng(kb));

        // 카메라 줌인(2.0f ~ 21.0f)
        mMap.animateCamera(CameraUpdateFactory.zoomTo(17.0f));

        /*/
        CameraPosition cp = new CameraPosition.Builder()
                .target(kb)
                .zoom(17.0f)
                .build();

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cp));

        // 마커 텍스트 클릭 이벤트
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() { //리스너 몸체 재정의
            @Override
            public void onInfoWindowClick(Marker marker) { //추상메소드 구현 //마커 클릭했을 때 하고자 하는 일 -> 전화 걸기 화면 띄운다
                Intent intent = new Intent(Intent.ACTION_DIAL); //전화 거는 액티비티에 대한 암시적 인텐트 생성
                intent.setData(Uri.parse("tel:02-999-1234")); //인텐트 객체에 setData //저런 전화번호가 있다고 가정

                startActivity(intent); //전화 걸 수 있는 화면에 대한 액티비티 실행시킬 수 있도록 구성
            }
        });

        // 보기 설정
        UiSettings ui = mMap.getUiSettings();

        // 줌 컨트롤
        ui.setZoomControlsEnabled(true);

        // 나침반 표시 여부
        //ui.setCompassEnabled(false);

        // 현재 나의 위치 표시 버튼 활성화 여부
        //ui.setMyLocationButtonEnabled(false);

        // 각종 제스처
        //ui.setScrollGesturesEnabled(false);
        //ui.setZoomGesturesEnabled(false);
        //ui.setRotateGesturesEnabled(false);
        //ui.setTiltGesturesEnabled(false);

        ui.setAllGesturesEnabled(true);

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                MarkerOptions maker = new MarkerOptions()
                        .position(latLng); //클릭 지점에 마커 설정
                mMap.addMarker(maker);

                CircleOptions circle = new CircleOptions()
                        .center(latLng)
                        .radius(100)
                        .strokeColor(Color.BLUE)
                        .strokeWidth(1.0f)
                        .fillColor(Color.parseColor("#220000FF"));
                mMap.addCircle(circle);
            }
        });
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public void mCurrentLocation(View v) {
        // 권한 체크
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[] { Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION},
                    REQUEST_CODE_PERMISSIONS);
            return;
        }

        mFusedLocationClient.getLastLocation().addOnSuccessListener(this,
                new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            // 현재 위치
                            LatLng myLocation = new LatLng(location.getLatitude(), location.getLongitude());
                            mMap.addMarker(new MarkerOptions()
                                    .position(myLocation)
                                    .title("현재 위치"));

                            mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation));

                            // 카메라 줌
                            //mMap.animateCamera(CameraUpdateFactory.zoomTo(17.0f));
                            mMap.moveCamera(CameraUpdateFactory.zoomTo(17.0f));
                        }
                    }
                });
    }

    //권한사항 요청 거부 시 토스트를 이용해서 확인 메시지 출력
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_CODE_PERMISSIONS:
                if (ActivityCompat.checkSelfPermission(this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(this,
                                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "권한 체크 거부 됨", Toast.LENGTH_SHORT).show();
                }
                return;
        }
    }
}
