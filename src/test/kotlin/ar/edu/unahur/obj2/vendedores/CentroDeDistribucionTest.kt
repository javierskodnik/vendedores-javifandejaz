package ar.edu.unahur.obj2.vendedores

import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe


class CentroDeDistribucionTest : DescribeSpec ({

    val misiones = Provincia(1300000)
    val sanIgnacio = Ciudad(misiones)

    val buenosAires = Provincia(16660000)
    val hurlingham = Ciudad(buenosAires)
    val moron = Ciudad(buenosAires)

    val mendoza = Provincia(2500000)
    val sanRafael = Ciudad(mendoza)

    val certificacionA = Certificacion(true, 15)
    val certificacionB = Certificacion(false, 10)
    val certificacionC = Certificacion(false, 5)

    val homero = Viajante(listOf(misiones))
    homero.agregarCertificacion(certificacionA)
    homero.agregarCertificacion(certificacionA)

    val carl = VendedorFijo(sanRafael)
    carl.agregarCertificacion(certificacionB)
    carl.agregarCertificacion(certificacionC)

    val leny = ComercioCorresponsal(mutableListOf( hurlingham, sanIgnacio))
    leny.agregarCertificacion(certificacionC)
    leny.agregarCertificacion(certificacionC)

    val centroA = CentroDeDistribucion(sanIgnacio, mutableListOf(homero, carl, leny))

    describe("Agregar vendedor"){
        val bart = Viajante(listOf(mendoza))
        centroA.agregaVendedor(bart)
        describe("Puede"){
            centroA.vendedores.shouldContainAll(homero,carl, leny,bart)
        }
        describe("No puede"){
            shouldThrowAny{centroA.agregaVendedor(homero)}
        }
    }

    describe("Vendedor estrella"){
        centroA.vendedorEstrella().shouldBe(homero)
    }

    describe("Cubrir"){
        describe("Puede"){
            centroA.puedeCubrir(sanRafael).shouldBeTrue()
        }
        describe("No puede"){
            centroA.puedeCubrir(moron).shouldBeFalse()
        }
    }

    describe("Vendedores genéricos"){
        centroA.vendedoresGenericos().shouldContainAll(carl, leny)
    }

    describe("Es robusto"){
        describe("No"){
            centroA.esRobusto().shouldBeFalse()
        }
        describe("Si"){
            val march = Viajante(listOf(buenosAires))
            march.agregarCertificacion(certificacionA)
            march.agregarCertificacion(certificacionA)
            val berns = Viajante(listOf(mendoza))
            berns.agregarCertificacion(certificacionA)
            berns.agregarCertificacion(certificacionA)

            centroA.agregaVendedor(march)
            centroA.agregaVendedor(berns)

            centroA.esRobusto().shouldBeTrue()
        }
    }

})