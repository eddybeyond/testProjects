
int led[]={4,5,6,7,8,9,10,11,12,13};
int boton[]={2,3};
int led_cantidad = 10;
int boton_cantidad = 2;
int tiempo_vuelta = 400;
int tiempo_ocioso = 200;
int cuenta = 0;
int cuenta_alto = 0;
int cuenta_ocioso = 0;
boolean boton_estado[] = {LOW, LOW};//derecha,izquierda
int derecha[3][3]={{0,8,6},{1,9,5},{2,3,4}};
int izquierda[3][3]={{2,9,4},{1,8,5},{0,7,6}};

void setup() {
  for(int i = 0; i < led_cantidad; i++){
    pinMode(led[i],OUTPUT);  
  }
  for(int j = 0; j < boton_cantidad; j++){
    pinMode(boton[j],INPUT);  
  }
}

void loop() {
  //apaga leds
  for(int i = 0; i < led_cantidad; i++){
    digitalWrite(led[i],LOW);  
  }
  //lee estado de botones
  for(int j = 0; j < boton_cantidad; j++){
    boton_estado[j] = digitalRead(boton[j]);  
  }
  if(boton_estado[0]==HIGH && boton_estado[1]==HIGH)
    alto();
  else if(boton_estado[0]==HIGH)
    direccion(derecha);
  else if(boton_estado[1]==HIGH)
    direccion(izquierda);
  else
    ocioso();
  
}

void direccion(int flechas[3][3]){
  for(int j = 0; j<3; j++){
    digitalWrite(led[flechas[cuenta][j]],HIGH);
  }
  delay (tiempo_vuelta);
  cuenta++;
  if(cuenta>2){
    cuenta = 0;  
  }
}

void alto(){
  if(cuenta_alto == 1){
    for(int i = 0; i<led_cantidad; i++){
      digitalWrite(led[i],HIGH);
    }
  } else {
    for(int i = 0; i<led_cantidad; i++){
      digitalWrite(led[i],LOW);
    }
  }
  delay (tiempo_vuelta);
  cuenta_alto++;
  if (cuenta_alto > 1){
    cuenta_alto = 0;
  }
  
}

void ocioso(){
  digitalWrite (led[cuenta_ocioso],HIGH);
  digitalWrite (led[cuenta_ocioso+4],HIGH);
  delay (tiempo_ocioso);
  cuenta_ocioso++;
  if (cuenta_ocioso > 3)
  {
    cuenta_ocioso = 0;
  }  
}
