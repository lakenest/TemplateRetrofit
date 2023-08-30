# TemplateRetrofit
Consumir api con retrofit y mvvm

Arquitectura MVVM como estrutura base tiene
* core *creamos un objeto retrofit*
 ```
  object RetrofitHelper {
  fun getRetrofit(): Retrofit {
  return Retrofit.Builder()
  .baseUrl("https://www.boredapi.com/api/")
  .addConverterFactory(GsonConverterFactory.create())
  .build()
  }
  ```
* data
  * models *Entity o modelo de bd*
  ```
  data class ParticipantsModel(
  @SerializedName("activity") val activity:String = "none",
  @SerializedName("type") val type:String,
  @SerializedName("participants") val participants:Int,
  @SerializedName("price") val price:Float,
  @SerializedName("link") val link:String,
  @SerializedName("key") val key:String,
  @SerializedName("accessibility") val accessibility:Float)
  ```
  * network 
    - IClassService *definimos los contratos*
    - ClassService *instanciamos retrofit*
      ```
      interface IParticipantsApiClient {
      @GET("activity")
      suspend fun getParticipants(@Query("participants") participants:Int): Response<ParticipantsModel>
      }
      ```    
      ```
      class ClassService {
      private val retrofit = RetrofitHelper.getRetrofit()
    
        suspend fun callParticipants(participantsId:Int): ParticipantsModel? {
            return withContext(Dispatchers.IO){
                val response= retrofit.create(IParticipantsApiClient::class.java).getParticipants(participantsId)
                response.body()
            }
        }
      }
      ```
  - ClassRepo *instanciamos clase servicio*
  ```
  class ParticipantsRepo{

    private val api = ParticipantsService()

    suspend fun getParticipantsId(participantsId:Int): ParticipantsModel? {
        val response = api.callParticipants(participantsId)
        return response
    }
  }
  ```
* domain *casos de uso, controladores por cada posible solicitud*
```
  class UseCaseGetParticipantsId {
  private val participantsRepo = ParticipantsRepo()

  suspend operator fun invoke(pantsId:Int): ParticipantsModel? {
  return participantsRepo.getParticipantsId(pantsId)
    }
  }
```

* ui
  * Participant
    - ClassActivity
    - ClassActivityVM *extiende de ViewModel*
  ```
  private val participantsViewModel:ParticipantsViewModel by viewModels()
  
  btnConsultar.setOnClickListener {
            participantsViewModel.onCreate(pantsId)
        }

        participantsViewModel.participantsModel.observe(this){ pantsModel ->
            if (pantsModel!=null){

                with(binding){
                    etActivity.setText(pantsModel.activity)
                    etType.setText(pantsModel.type)
                    etParticipants.setText(pantsModel.participants.toString())
                    etPrice.setText(pantsModel.price.toString())
                    etLink.setText(pantsModel.link)
                    etKey.setText(pantsModel.key)
                    etAccesibility.setText(pantsModel.accessibility.toString())



                }
            }
  
  ```   

  ```
  class ParticipantsViewModel: ViewModel() {
  var participantsModel = MutableLiveData<ParticipantsModel?>()
  val useCaseGetParticipantsId = UseCaseGetParticipantsId()
  fun onCreate(panstId:Int){
      viewModelScope.launch {
          val result = useCaseGetParticipantsId(panstId)
          participantsModel.postValue(result)
      }
    } 
  }
  ```


**Diseño xml applicacion base**

![image](https://github.com/lakenest/TemplateRetrofit/assets/42565684/f50e6baa-36a4-4e60-bd7a-83a30976eaf8)

**Vista previa ejecutando aplicacion**

![image](https://github.com/lakenest/TemplateRetrofit/assets/42565684/06233fdf-6313-4940-bab6-d516b17bbae9)

