package com.wazunga.findit.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.wazunga.findit.R
import com.wazunga.findit.adapters.PlacesAdapter
import com.wazunga.nhulox97.findit.models.Place
import com.wazunga.nhulox97.findit.models.Result

class PlaceActivity : AppCompatActivity() {

    lateinit var mRecyclerView: RecyclerView
    val mAdapter: PlacesAdapter = PlacesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place)

        val extras = intent.extras
        var res = extras?.getString("response")
        /*
        res =  """
            {
       "html_attributions" : [],
       "results" : [
          {
             "geometry" : {
                "location" : {
                   "lat" : -33.8677371,
                   "lng" : 151.2016936
                },
                "viewport" : {
                   "northeast" : {
                      "lat" : -33.86637842010727,
                      "lng" : 151.2031597798928
                   },
                   "southwest" : {
                      "lat" : -33.86907807989272,
                      "lng" : 151.2004601201073
                   }
                }
             },
             "icon" : "https://maps.gstatic.com/mapfiles/place_api/icons/generic_business-71.png",
             "id" : "f1e044040bd03ff06e19de4798b52dd926855281",
             "name" : "Sydney Harbour Dinner Cruises",
             "opening_hours" : {
                "open_now" : true
             },
             "photos" : [
                {
                   "height" : 678,
                   "html_attributions" : [
                      "\u003ca href=\"https://maps.google.com/maps/contrib/109764923610545394994/photos\"\u003eA Google User\u003c/a\u003e"
                   ],
                   "photo_reference" : "CmRaAAAAC7eUlzZ8tryOJJ6o6VlqqSx6mn6A6a8NKnYGU_4VxTJHyrPFYprajKRbf1b9IFE2-p1blk-Q11r6uwePI1rS1i5__ErIiTGxk7cLJfwp1kjfmizovXyFLadgy6TbgUR_EhCK6WXeGDI-wF9TzLnGh7xCGhTo44knXtSLg65Znie6VVeE24c2tg",
                   "width" : 1046
                }
             ],
             "place_id" : "ChIJM1mOVTS6EmsRKaDzrTsgids",
             "plus_code" : {
                "compound_code" : "46J2+WM Sydney, New South Wales",
                "global_code" : "4RRH46J2+WM"
             },
             "rating" : 5,
             "reference" : "ChIJM1mOVTS6EmsRKaDzrTsgids",
             "scope" : "GOOGLE",
             "types" : [
                "tourist_attraction",
                "travel_agency",
                "restaurant",
                "food",
                "point_of_interest",
                "establishment"
             ],
             "user_ratings_total" : 2,
             "vicinity" : "32 The Promenade, Sydney"
          },
          {
             "geometry" : {
                "location" : {
                   "lat" : -33.8686058,
                   "lng" : 151.2018206
                },
                "viewport" : {
                   "northeast" : {
                      "lat" : -33.86724947010728,
                      "lng" : 151.2032704798927
                   },
                   "southwest" : {
                      "lat" : -33.86994912989272,
                      "lng" : 151.2005708201072
                   }
                }
             },
             "icon" : "https://maps.gstatic.com/mapfiles/place_api/icons/generic_business-71.png",
             "id" : "21a0b251c9b8392186142c798263e289fe45b4aa",
             "name" : "Rhythmboat Cruises",
             "opening_hours" : {
                "open_now" : true
             },
             "photos" : [
                {
                   "height" : 2269,
                   "html_attributions" : [
                      "\u003ca href=\"https://maps.google.com/maps/contrib/104066891898402903288/photos\"\u003eRhythmboat Sydney Harbour Cruises\u003c/a\u003e"
                   ],
                   "photo_reference" : "CmRaAAAAHvAkbXTh7T21sivw_3EJPiit03IxGdl-0K47Z3xUBOJoq5Q7mY8k69jVJqbH-eBMYD7tCiCUVfF7ThBNlj6VGNCv1vJaOH4MEOr3Unv66WWm4_jEe3Ux4U-5MVXlWajPEhC5wC_GcazI7C-ChdbV-Ah9GhSkjTG0TCxZJWZd84ecusMKUFe-XQ",
                   "width" : 4032
                }
             ],
             "place_id" : "ChIJyWEHuEmuEmsRm9hTkapTCrk",
             "plus_code" : {
                "compound_code" : "46J2+HP Sydney, New South Wales",
                "global_code" : "4RRH46J2+HP"
             },
             "rating" : 4,
             "reference" : "ChIJyWEHuEmuEmsRm9hTkapTCrk",
             "scope" : "GOOGLE",
             "types" : [
                "travel_agency",
                "restaurant",
                "food",
                "point_of_interest",
                "establishment"
             ],
             "user_ratings_total" : 27,
             "vicinity" : "King Street Wharf, King St, Sydney"
          }
       ],
       "status" : "OK"
    }
            """

         */
        val places = getPlaces(res!!)

        setUpRecyclerView(places)
    }

    private fun setUpRecyclerView(places: ArrayList<Place>) {
        mRecyclerView = findViewById(R.id.rv_place_list) as RecyclerView
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter.placesAdapter(places, this)
        mRecyclerView.adapter = mAdapter
    }

    private fun getPlaces(res: String): ArrayList<Place> {
        val result = Gson().fromJson(res, Result::class.java)
        return result.results
    }
}
