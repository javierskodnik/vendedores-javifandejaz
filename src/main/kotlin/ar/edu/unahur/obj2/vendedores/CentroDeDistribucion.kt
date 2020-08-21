package ar.edu.unahur.obj2.vendedores

class CentroDeDistribucion(val ciudad: Ciudad, var vendedores: MutableList<Vendedor>){

    fun agregaVendedor(vendedor: Vendedor){
        if (!vendedores.contains (vendedor))
            vendedores.add(vendedor)
        else
            throw Exception("Se presento un error inesperado!")
    }

    fun vendedorEstrella() = vendedores.maxBy { v -> v.puntajeCertificaciones()}

    fun puedeCubrir(ciudad: Ciudad)= vendedores.any { v -> v.puedeTrabajarEn(ciudad) }

    fun vendedoresGenericos(): List<Vendedor> {

        return vendedores.filter { v -> v.certificaciones.any{ c -> !c.esDeProducto } }

    }

    fun esRobusto()= vendedores.count{ v -> v.esFirme()} >= 3


}